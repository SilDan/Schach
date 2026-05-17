#!/usr/bin/env bash

set -euo pipefail

TARGET_DIR="src/main/resources/pieces"

mkdir -p "$TARGET_DIR"

download_piece() {
  local url="$1"
  local target_name="$2"
  local target_file="${TARGET_DIR}/${target_name}"

  echo "Downloading ${target_name}"

  curl -L \
    --retry 3 \
    --retry-delay 2 \
    -A "Mozilla/5.0" \
    "$url" \
    -o "$target_file"

  if ! grep -q "<svg" "$target_file"; then
    echo "ERROR: ${target_name} is not a valid SVG file."
    echo "First lines:"
    head -n 10 "$target_file"
    exit 1
  fi
}

download_piece "https://upload.wikimedia.org/wikipedia/commons/4/42/Chess_klt45.svg" "white_king.svg"
download_piece "https://upload.wikimedia.org/wikipedia/commons/1/15/Chess_qlt45.svg" "white_queen.svg"
download_piece "https://upload.wikimedia.org/wikipedia/commons/7/72/Chess_rlt45.svg" "white_rook.svg"
download_piece "https://upload.wikimedia.org/wikipedia/commons/b/b1/Chess_blt45.svg" "white_bishop.svg"
download_piece "https://upload.wikimedia.org/wikipedia/commons/7/70/Chess_nlt45.svg" "white_knight.svg"
download_piece "https://upload.wikimedia.org/wikipedia/commons/4/45/Chess_plt45.svg" "white_pawn.svg"

download_piece "https://upload.wikimedia.org/wikipedia/commons/f/f0/Chess_kdt45.svg" "black_king.svg"
download_piece "https://upload.wikimedia.org/wikipedia/commons/4/47/Chess_qdt45.svg" "black_queen.svg"
download_piece "https://upload.wikimedia.org/wikipedia/commons/f/ff/Chess_rdt45.svg" "black_rook.svg"
download_piece "https://upload.wikimedia.org/wikipedia/commons/9/98/Chess_bdt45.svg" "black_bishop.svg"
download_piece "https://upload.wikimedia.org/wikipedia/commons/e/ef/Chess_ndt45.svg" "black_knight.svg"
download_piece "https://upload.wikimedia.org/wikipedia/commons/c/c7/Chess_pdt45.svg" "black_pawn.svg"

echo "Done. SVG chess pieces are in ${TARGET_DIR}"