# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Java learning/tutorial project using Maven. It contains minimal starter code and is intended for learning Java fundamentals.

- **Java Version**: 11
- **Build Tool**: Maven
- **Main Class**: `com.zhucj.java.Main`

## Common Commands

```bash
# Compile the project
mvn clean compile

# Run the main class
mvn exec:java

# Run tests
mvn test

# Run a single test class
mvn test -Dtest=TestClassName

# Build JAR file
mvn package
```

## Project Structure

```
src/
├── main/java/com/zhucj/java/
│   ├── Main.java          # Entry point
│   └── basicdata/         # Basic data examples (expandable)
└── test/                  # JUnit 5 tests
```

## Dependencies

- **JUnit 5.9.2** - Testing framework
- **Apache Commons Lang3 3.12.0** - Utility library

## Notes

- Tests directory exists but is currently empty
- The project follows standard Maven directory layout