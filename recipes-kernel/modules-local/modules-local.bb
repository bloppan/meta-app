DESCRIPTION = "Hello kernel module out of the kernel tree"
SECTION = "Local kernel modules examples"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit module

SRC_URI = " \
		file://hello.c \
		file://Makefile \
		"

S = "${WORKDIR}"



