DESCRIPTION = "Linux kernel modules"
SECTION = "Remote Linux kernel modules"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRCREV = "${AUTOREV}"
SRCREV_FORMAT = "none"

SRC_URI = "git://github.com/bloppan/linux_kernelspace.git;destsuffix=git/linux_kernelspace;protocol=https;branch=main "

S = "${WORKDIR}/git/linux_kernelspace/"

FILES_${PN} = "${base_libdir}/modules "

inherit module


