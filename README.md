# BabelJ

A command line tool to translate text from the clipboard or selected text.
A simple tool for those who would not survive in the tower of Babel.

## Config file

Example of a simple settings file (default `~/.BabelJ.json`) containing _all_ the settings entries:

```json
{
  "default_backend": "yandex",
  "yandex_key": "YourYandexApiKey",
  "microsoft_key": "YourMicrosoftApiKey",
  "microsoft_id": "YourMicrosoftId@outlook.com",
  "google_key": "YourGoogleApiKey",
  "google_id": "YourGoogleId@gmail.com",
  "default_language": "en",
  "default_input": "selection",
  "default_output": "notify",
  "default_exchange": false
}
```
## TODO FIX CHANGES FROM PYTHON·TO JAVA
## Usage examples

You can simply run `babelPy` to run translation, picking preferences from default settings file (`~/.babelPy.json`).

Also you can override config file settings using CLI arguments.

Supports the **@** syntax, which allows you to put all your options into a file and pass this file as parameter:

```bash
java -jar babelj.jar @/tmp/parameters
```

Example of `babelPY -h` or `babelPy --help`  output:

Note: All of each _cli_ options overrides their corresponding settings settings stored on the settings file(s).

```
Usage: <babelj.jar> [options]
  Options:
    --help, -h
      Show help and usage options
      Default: false
    --api-id, -id
      Translate backend ID. [yourId@server.com]
    --api-key, -a
      Translate backend API key. [yourApiKey]
    --backend, -b
      Target translate backend. [yandex|microsoft|google]
    --config-file, -c
      Custom path to config file. [/path/to/config.json]
    --exchange, -x
      Exchange clipboard content with translation result.
      Default: false
    --input, -i
      Desired input method. [clipboard|selection]
    --message, -m
      The text to translate itself given as a String argument.
    --output, -o
      Desired output method. [notify|stdout|none]
    --save-config
      Save (overwriting) current settings to config file (into default or 
      given path) and exit.
      Default: false
    --source-lang, -s
      Source language to translate from. [en|ru|fr|es|...]
    --target-lang, -t
      Target language to translate. [en|ru|fr|es|...]
```
