@echo off
javac -cp "lib/sqlite-jdbc-3.42.0.0.jar" SafetyAlarm.java
if %errorlevel% equ 0 (
    java -cp ".;lib/sqlite-jdbc-3.42.0.0.jar" SafetyAlarm
)
