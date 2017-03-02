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

Example of `babelPY -h` or `babelPy --help`  output:

Note: All of each _cli_ options overrides their corresponding settings settings stored on the settings file(s).

```
usage: babelPy.py [-h] [-a [YourApiKey]] [-b [yandex|other]]
                  [-c [.babelPy.json]] [-s [en|es]] [-t [en|es]]
                  [-m [Text to translate]] [-i [clipboard|selection]]
                  [-o [stdout|notify|dialog|none]] [-x] [--save-settings]

An easy tool for those who would not survive in the tower of Babel

optional arguments:
  -h, --help            show this help message and exit
  -a [YourApiKey], --api-key [YourApiKey]
                        Your API key for target (or default) backend
  -b [yandex|other], --backend [yandex|other]
                        Target translate backend (Default: yandex)
  -c [.babelPy.json], --settings-file [.babelPy.json]
                        Path to settings file (load and save)
  -s [en|es], --source-lang [en|es]
                        Give a source targetLang (avoids auto detection)
  -t [en|es], --target-lang [en|es]
                        Give a target targetLang (overrides settings)
  -m [Text to translate], --message [Text to translate]
                        Pass directly the actual text to translate as an
                        argument (overrides clipboard and selection)
  -i [clipboard|selection], --input [clipboard|selection]
                        From where the text has to be taken
  -o [stdout|notify|dialog|none], --output [stdout|notify|dialog|none]
                        Where to (out)put the translation
  -x, --exchange        Exchange/paste translation to clipboard
  --save-config         Save a settings file at default (or -c given) path,
                        based on default or stored/saved settings.

Enjoy!
```
