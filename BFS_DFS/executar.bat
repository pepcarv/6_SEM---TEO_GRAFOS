@echo off
echo Compilando:
if not exist bin mkdir bin

javac -d bin src\GrafoMatriz\*.java src\PPilha\*.java src\FFilaCircular\*.java

if %errorlevel% neq 0 (
    echo.
    echo Erro: compilacao.
    pause
    exit /b %errorlevel%
)

echo.
echo Executando
echo.
java -cp bin GrafoMatriz.TesteGrafoMatriz

echo.
pause
