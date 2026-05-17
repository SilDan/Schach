package org.sildan.gui;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.image.ImageTranscoder;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Loads SVG files from the classpath and converts them into JavaFX images.
 */
public class SvgImageLoader {

    private final Map<String, Image> imageCache = new HashMap<>();

    /**
     * Loads an SVG image from the classpath.
     *
     * @param resourcePath the classpath resource path
     * @param width        the target width
     * @param height       the target height
     * @return the rendered JavaFX image
     */
    public Image loadSvg(String resourcePath, double width, double height) {
        String cacheKey = resourcePath + "#" + width + "x" + height;
        return imageCache.computeIfAbsent(cacheKey, ignored -> renderSvg(resourcePath, width, height));
    }

    private Image renderSvg(String resourcePath, double width, double height) {
        String url = Objects.requireNonNull(
                getClass().getResource(resourcePath),
                "SVG resource not found: " + resourcePath
        ).toExternalForm();

        BufferedImageTranscoder transcoder = new BufferedImageTranscoder();

        transcoder.addTranscodingHint(ImageTranscoder.KEY_WIDTH, (float) width);
        transcoder.addTranscodingHint(ImageTranscoder.KEY_HEIGHT, (float) height);

        try {
            transcoder.transcode(new TranscoderInput(url), null);
            return SwingFXUtils.toFXImage(transcoder.getBufferedImage(), null);
        } catch (TranscoderException exception) {
            throw new IllegalStateException("Could not render SVG resource: " + resourcePath, exception);
        }
    }

    private static class BufferedImageTranscoder extends ImageTranscoder {

        private BufferedImage bufferedImage;

        @Override
        public BufferedImage createImage(int width, int height) {
            return new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        }

        @Override
        public void writeImage(BufferedImage image, org.apache.batik.transcoder.TranscoderOutput output) {
            this.bufferedImage = image;
        }

        public BufferedImage getBufferedImage() {
            return bufferedImage;
        }
    }
}