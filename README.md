# dp-08-factory

This workspace contains two design-pattern demos:

- `factory_method` — a notification example using the Factory Method pattern.
- `factory_abstract` — a notification example using the Abstract Factory pattern.
- `dessin` — a JavaFX paint app built with Factory Method.

## Factory Method example
The `factory_method` package creates one notification at a time:
- `NotificationFactory` is the creator abstraction.
- `SMSNotificationFactory` and `EmailNotificationFactory` are concrete creators.
- `SMSNotification` and `EmailNotification` are the concrete products.

## Abstract Factory example
The `factory_abstract` package creates three related products for each family:
- Families: `SMS` and `EMAIL`
- Products in each family: `alert`, `rapport`, and `confirmation`
- `NotificationFactory` is the abstract factory.
- `SMSFactory` and `EmailFactory` create consistent product families.

## JavaFX paint app
The `dessin` package applies Factory Method to shapes:
- `ShapeFactory` is the creator abstraction.
- `RectangleShapeFactory`, `CircleShapeFactory`, and `LineShapeFactory` are concrete creators.
- `PaintApp` uses the selected factory to create drawable shapes.

## Run
If JavaFX is installed at `C:\javafx-sdk-21.0.10\lib`, you can compile and run from PowerShell:

```powershell
$javafx = 'C:\javafx-sdk-21.0.10\lib'
$classes = 'out'
New-Item -ItemType Directory -Force $classes | Out-Null
javac --module-path $javafx --add-modules javafx.controls -d $classes (Get-ChildItem -Path src -Recurse -Filter *.java | ForEach-Object { $_.FullName })
java --module-path $javafx --add-modules javafx.controls -cp $classes dessin.PaintApp
```

### Abstract Factory demo

```powershell
java -cp out factory_abstract.Client
```

# MASI_factory
