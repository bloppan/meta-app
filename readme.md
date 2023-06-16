# Yocto Project example layer
The Yocto Project is an open source collaboration project that helps developers create custom Linux-based systems regardless of the hardware architecture.
This repository contains an easy example of a distro configuration layer where you can see how to: 
- Add files to the embedded Linux distribution.
- Customize the number and type of the filesystem partitions.
- Add new init services.
- Configure the device tree.
- Configure the Linux kernel.
- Download, build and install userspace applications and shared libraries examples.
- Download, build and install kernel modules examples.

The image has been tested on the system on module Variscite DART-6UL. For futher information visit: [DART-6UL - Yocto Dunfell Guide](https://variwiki.com/index.php?title=Yocto_Build_Release&release=RELEASE_DUNFELL_V1.1_DART-6UL)

# Quick Start
Please make sure your host PC is running Ubuntu 18.04/20.04 64-bit and is up to date:
```sh
$ sudo apt-get update && sudo apt-get dist-upgrade
```
Install the following packages:
```sh
$ sudo apt-get install gawk wget git diffstat unzip texinfo gcc-multilib \
build-essential chrpath socat cpio python python3 python3-pip python3-pexpect \
xz-utils debianutils iputils-ping libsdl1.2-dev xterm
```
```sh
$ sudo apt-get install autoconf libtool libglib2.0-dev libarchive-dev python-git 
sed cvs subversion coreutils texi2html docbook-utils python-pysqlite2 \
help2man make gcc g++ desktop-file-utils libgl1-mesa-dev libglu1-mesa-dev \
mercurial automake groff curl lzop asciidoc u-boot-tools dos2unix mtd-utils pv \
libncurses5 libncurses5-dev libncursesw5-dev libelf-dev zlib1g-dev bc rename python3-git
```
Configure your GIT account:
```sh
$ git config --global user.name "Your Name"
$ git config --global user.email "Your Email"
```
Download Yocto Dunfell based on Freescale Community BSP 3.1 
```sh
$ mkdir ~/bin (this step may not be needed if the bin folder already exists)
$ curl https://commondatastorage.googleapis.com/git-repo-downloads/repo > ~/bin/repo
$ chmod a+x ~/bin/repo
$ export PATH=~/bin:$PATH
$ mkdir ~/var-fslc-yocto
$ cd ~/var-fslc-yocto
$ repo init -u https://github.com/varigit/variscite-bsp-platform.git -b dunfell
$ repo sync -j$(nproc)
```
Build X11 GUI demo image
```sh
$ cd ~/var-fslc-yocto
$ MACHINE=imx6ul-var-dart DISTRO=fslc-x11 . setup-environment build_x11
```
The above command is only mandatory for the very first build setup: whenever restarting a newer build session (from a different terminal or in a different time), you can skip the full setup and just run:
```sh
$ cd ~/var-fslc-yocto
$ source setup-environment build_x11
```
Now add this Yocto Project example layer named "meta-app":
```sh
$ cd ~/var-fslc-yocto/sources/
$ git clone https://github.com/bloppan/meta-app.git
$ cd ~/var-fslc-yocto/build_x11
$ bitbake-layers add-layer ../sources/meta-app
```
Run this command to build the image:
```sh
$ bitbake app-image
```

# How works Yocto Project?
### Layer Model
Yocto Project has a Layer Model that is designed to support both collaboration and customization at the same time. Layers are repositories containing related sets of instructions which tell the build system what to do. Users can collaborate, share, and reuse layers. Layers can contain changes to previous instructions or settings at any time.

This powerful override capability is what allows you to customize previous collaborative or community supplied layers to suit your product requirements.

Use different layers to logically separate information in your build. As an example, you could have a BSP layer, a GUI layer, a distro configuration, middleware, or an application. Putting your entire build into one layer limits and complicates future customization and reuse. Isolating information into layers, on the other hand, helps simplify future customizations and reuse. Use BSP layers from silicon vendors when possible.

The layers are under "sources/" directory:
```sh
├── build_x11
├── downloads
├── README -> sources/base/README
├── setup-environment -> sources/base/setup-environment
└── sources/
        ├── base
        ├── Documentation
        ├── meta-app
        ├── meta-freescale
        ├── meta-freescale-3rdparty
        ├── meta-freescale-distro
        ├── meta-openembedded
        ├── meta-python2
        ├── meta-qt5
        ├── meta-swupdate
        ├── meta-variscite-fslc
        ├── meta-variscite-hab
        ├── meta-virtualization
        └── poky
```

You can check the project layers running the following command:
```sh
$ bitbake-layers show-layers
```

### Recipes
Layers are usually repositories that contains a set of files (with .bb extension) named recipes. This files have all the instructions and configurations which tell the build system what to do. Also it has the information of where are the source code or patches to apply and futher. 
In this layer example, the recipes are isolated in four types:
- recipes-filesystem: Add new files and init services to the image.
- recipes-images: Recipes that defines two image types.
- recipes-kernel: Apply kernel and device tree configurations.
- recipes-sofware: Download, compile and install apps and libraries.

You can find other layer configurations under conf/ and wic/ directories.

# Yocto Project cheat sheet
Bitbake is the principal build tool. Is a make-like build tool which can build only one recipe, build the whole image, build only the kernel, and more.

#### Layers:
```sh
$ bitbake-layers create-layer ../sources/meta-app/
$ bitbake-layers add-layer ../sources/meta-app/
```
Check layers:
```sh
$ bitbake-layers show-layers
```
You can also check this file:
```sh
$ cat ~/var-fslc-yocto/build_x11/conf/bblayers.conf
```

#### Recipes:
Bitbake allows build recipes individually. This can be usefull for testing purposes. If you want build the recipe helloworld.bb, run:
```sh
$ bitbake helloworld
```

To review if the objects generated with the previous recipe are correct, run:
```sh
$ bitbake -c devshell helloworld
```

#### Kernel and device tree
##### Source code
To modify the kernel source code, run:
```sh
$ devtool modify virtual/kernel
```
A copy of Linux kernel source code will be installed in the directory  ~/var-fslcyocto/build_x11/workspace/sources/linux-variscite/

Modify the source code and then run:
```sh
git add .
git commit -m "patch name"
```
Now you can create the patch and the append recipe "linux-variscite.bbappend" which apply it when the original recipe "linux-variscite.bb" be built.

```sh
$ devtool update-recipe -a ~/var-fslc-yocto/sources/meta-app linux-variscite
```
When finishing the modifications run:
```sh
$ devtool reset linux-variscite
```
##### Configurations
Configure the Linux kernel through menuconfig:
```sh
$ bitbake -c menuconfig virtual/kernel
```
Save the new configuration:
```sh
$ bitbake -c savedefconfig virtual/kernel
```
It will create a new defconfig file which must be copied to ~/var-fslc-yocto/sources/meta-app/recipes-kernel/linux/linux-variscite/defconfig

# Images
You can have images with different configurations, such as production and debug images, for example. For each image, you will have one recipe. In this example the images availables are in ~/var-fslc-yocto/sources/meta-app/recipes-images/images/.
Build the image running:
```sh
$ bitbake app-image
```
Or:
```sh
$ bitbake recovery-image
```
You can find the images and objects generated in ~/var-fslc-yocto/build_x11/tmp/deploy/images/imx6ul-var-dart-app/

Decompress app-image-imx6ul-var-dart-app.wic.gz to get the Linux distribution image file. Flash the image in a SD card and try it in Variscite DART-6UL.
```sh
$ dd if=data of=/dev/sda
```

# References

- [Desarrollo de un sistema de soporte para sondas moviles Android con Yocto Project](https://riunet.upv.es/bitstream/handle/10251/181908/Lopez%20-%20DESARROLLO%20DEL%20SOFTWARE%20DE%20UN%20SISTEMA%20DE%20SOPORTE%20PARA%20SONDAS%20MOVILES%20ANDROID%20CON%20YOCTO%20PR....pdf?sequence=1&isAllowed=y)
- [Variscite Wiki](https://variwiki.com/index.php?title=Yocto_Build_Release&release=RELEASE_DUNFELL_V1.1_DART-6UL)
- [Variscite products 2021]( https://www.variscite.com/product/systemon-module-som/cortex-a7/dart-6ul-freescale-imx-6ul/#generalinfo)
- [Mega Manual Yocto Project](https://docs.yoctoproject.org/)
















