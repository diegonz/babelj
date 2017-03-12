# BabelJ

## Purpose
The goal of this project is to make the easiest, fastest, and most painless way of translating text on the go, which can run across **ALL platforms** supported by *Java* (including Linux, Mac OS X, IllumOS(Solaris), BSD *nixes, Windows and ARM).

BabelJ is a *simple* command line tool, designed to *simply* translate text.

## Features

- Get input from system *clipboard*, user *selection* or passing text as a *CLI* argument.
- Store and load settings from a simple *JSON* file.
- Use *yandex*, *microsoft* or *google* as translation backend.
- Use *CLI* arguments to load/overwrite settings.
- Show the obtained translated text outputting to *stdout* or as a OS-independent notification.
- Put the translation into system clipboard.

## Requirements

- Java 8 JRE/JDK (OpenJDK/Oracle).

### Optional dependencies

#### Linux

- libnotify *(Installed by default on most of gnome-based distributions)*.

#### Mac Os X

- terminal-notifier *(https://github.com/julienXX/terminal-notifier)*.

## Installation

- One size fits all: Download latest released *jar* from [Releases](https://github.com/gonzalez-diego/babelj/releases).
- You can also build yourself the *jar* executable using the default `shadowJar` gradle task included (bundling the code alongside the libraries).

## Config file

Below an example of the settings file placed by default at the root of your user folder and named `.babelj.json`.

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

## Usage examples

- You can run `java -jar babelj` to run the translation, picking preferences from default settings file (`~/.babelj.json`).

- You can also override config file settings using CLI arguments.

- Also supports the **@** syntax, which allows you to put all your options into a file and pass this file as parameter:

  ```bash
  java -jar babelj.jar @/tmp/parameters
  ```
- You can create a default (or *CLI-fed*) initial config file, using the `--save-config` flag alongside your preferred *CLI* arguments.

Below a copy of the output of running `java -jar babelj.jar` command using `-h` or `--help` flag, to show all available *CLI* arguments.

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
*Note: Each _CLI_ arguments used, overrides their corresponding settings stored/loaded from the config file(s).*

## License

This project is under the GPLv3 License. See the [LICENSE](LICENSE) file for the full license text.
