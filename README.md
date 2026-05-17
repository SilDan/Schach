# Schach

Chess engine concept that does not use fixed, human-defined material values.

## Core idea

Instead of assigning static values (for example "queen = 9"), piece value is evaluated dynamically from its current and future move possibilities.  
The engine tries to maximize forced move options and naturally derives useful chess principles from that objective.

## Design goals

- No hardcoded human play heuristics such as fixed piece values.
- Position-dependent piece valuation based on mobility and available continuations.
- Capability to discover strong behavior (e.g. mating attacks, defense of threatened pieces) from move-option maximization rather than explicit chess knowledge.

By design, this project uses a simplified terminal-state model: a side loses when no legal move options remain, represented internally by the king being removed from the board (instead of standard chess checkmate notation).
