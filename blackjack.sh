mvn -q install
echo "Starting game with $1 players"
mvn -q exec:java -Dexec.mainClass=com.dsddet.Main -Dexec.args="$1"