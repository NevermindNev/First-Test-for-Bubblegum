#!/bin/bash
# Simple build script for Bubblegum HelloWorld API
# This script provides alternative build methods if Maven is not available

echo "🔧 Building Bubblegum HelloWorld API..."

# Check for Java
if ! command -v java &> /dev/null; then
    echo "❌ Java is not installed. Please install Java 17 or higher."
    exit 1
fi

echo "✅ Java version:"
java -version

# Try Maven first
if command -v mvn &> /dev/null; then
    echo "📦 Using Maven to build..."
    mvn clean package
    if [ $? -eq 0 ]; then
        echo "✅ Build successful with Maven!"
        echo "🏃 Run with: java -jar target/first-test-1.0.0.jar"
        exit 0
    fi
fi

echo "⚠️  Maven not found or build failed. Creating simple compile script..."

# Create compile script
cat > compile.sh << 'EOF'
#!/bin/bash
# Simple Java compile script
mkdir -p target/classes
javac -cp "lib/*" -d target/classes src/main/java/com/bubblegum/*.java
if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    echo "To run: java -cp \"target/classes:lib/*\" com.bubblegum.HelloWorldAPI"
fi
EOF

chmod +x compile.sh

echo "📋 Manual build instructions:"
echo "1. Download dependencies to lib/ directory:"
echo "   - spark-core-2.9.4.jar"
echo "   - gson-2.10.1.jar"
echo "   - slf4j-simple-2.0.9.jar"
echo "2. Run: ./compile.sh"
echo "3. Run: java -cp \"target/classes:lib/*\" com.bubblegum.HelloWorldAPI"

echo ""
echo "🎯 Quick test without building:"
echo "The source code is ready in src/main/java/com/bubblegum/HelloWorldAPI.java"
echo "You can review it and compile when Java is fully set up."