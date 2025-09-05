
Convert modern decimal pounds (e.g., `10.23`) to pre-1971 £–s–d and print aligned results; read values from stdin and `money.txt`.

## Dependencies

* `sheffield.EasyReader` (console/file input)
* `sheffield.EasyWriter` (formatted console output)

## Constants

* `POUNDS_TO_PENNIES = 100.0f` (new pence per pound)
* `POUNDS_TO_OLD_PENNIES = 240.0f` (old pence per pound)
* `SHILLINGS_TO_PENCE = 5` (new pence per shilling)

## Static Fields

* `int pounds, pennies, shillings, oldPennies` — latest conversion result
* `EasyWriter writer` — shared output writer

## Entry Point: `main(String[] args)`

1. **Console input**

   * Read `double firstAmount` via `EasyReader.readDouble(...)`.
   * `converting(firstAmount)`.
   * `writer = new EasyWriter();`
   * `writer.prompt("That is £"+pounds+"."+shillings+"s."+oldPennies+"d\n");`
   * Print header: `"\n   New     L     s     d\n"`.
   * `addResults(firstAmount)`.

2. **File input — first number**

   * `EasyReader readerForFile = new EasyReader("money.txt");`
   * `double secondAmount = readerForFile.readDouble();`
   * `converting(secondAmount); addResults(secondAmount);`

3. **File input — parse number from next line**

   * `String lastLine = readerForFile.readString();`
   * `double thirdAmount = Double.valueOf(lastLine.substring(lastLine.indexOf("t")+2, lastLine.indexOf("to")-1));`
   * `converting(thirdAmount); addResults(thirdAmount);`

## Core Logic

### `converting(double amount)`

```java
pounds    = (int) amount;                        // whole pounds
pennies   = (int) ((amount - pounds) * 100);     // new pence (truncated)
shillings = pennies / 5;                         // 5 new pence per shilling
oldPennies = Math.round(
  (pennies % 5) / 100.0f * 240.0f               // leftover new pence → old pence
);
```

### `addResults(double amount)`

```java
writer.print(amount, 2, 6);   // New (2 dp, width 6)
writer.print(pounds, 6);      // L
writer.print(shillings, 6);   // s
writer.println(oldPennies, 6);// d
```

## I/O Format

* Header: `"   New     L     s     d"`
* Rows: aligned columns via `EasyWriter.print/println`.

## Assumptions / Limitations

* Truncates new pence (`(int)`), then **rounds** old pence; no carry from `d→s` or `s→L`.
* Parsing for third amount is brittle: requires `"t "` and `" to"` delimiters.
* No error handling for missing/invalid `money.txt` or bad input.
* Shared static state holds only the **most recent** conversion.

