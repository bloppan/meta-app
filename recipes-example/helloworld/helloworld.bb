SUMMARY = "A simple Hello World application"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
SRC_URI = "file://hello_world.c"

TARGET_CC_ARCH += "${LDFLAGS}"

S = "${WORKDIR}"

do_compile() {
  ${CC} -Wall hello_world.c -o hello_world
}

do_install() {
  install -d ${D}${bindir}
  install -m 0755 ${S}/hello_world ${D}${bindir}
}
