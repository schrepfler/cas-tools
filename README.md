# cas-tools
Set of command line tools to interact with the CAS REST API

## Developers

### Build instructions

### Prerequisites

* LVM 3.7+
* gcc
* libcurl with `curl.h` header file and OpenSSL support
* libgc
* libunwind
* libre2
* nexe (for JS/Windows builds)

#### macOS

Ensure XCode is updated to Apple's latest version. With Apple's latest XCode version, the minimum LLVM version is satisfied, so Homebrew install is not required.

Once XCode is updated to Apple's latest version, execute the following command to setup the project:

```bash
$ brew install bdw-gc re2 jq && \
    brew install curl --with-openssl
```

```sh
./build-mac-osx
```
