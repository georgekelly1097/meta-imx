# Copyright (C) 2019-2021,2023 NXP

SUMMARY = "Test program for v2x adaptation layer v2xhsm"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=664939843ae3416d811479b21978e8b4"

DEPENDS = "ecdsa-lib-imx v2xsehsm openssl"

SRC_URI = "${VTEST_SRC};branch=${SRCBRANCH} \
    file://seco_nvm_daemon.service \
    file://0001-Ignore-v2xsehsmConfig.cmake.patch \
    file://0001-CMakeLists.txt-Add-no-deprecated-declarations-to-fix.patch \
"
VTEST_SRC ?= "git://github.com/nxp-imx/vtest.git;protocol=https"
SRCBRANCH = "v2x-dev"
SRCREV = "b45bb7e4630d15cfeca4234c6f4d149efeb43c6b"

S = "${WORKDIR}/git"

inherit systemd cmake

PACKAGECONFIG ?= ""
PACKAGECONFIG[lttng] = "-DLTTNG_LIB=${STAGING_DIR_TARGET}${libdir}/liblttng-ust.so,,lttng-ust"

SYSTEMD_SERVICE:${PN} = "seco_nvm_daemon.service"

EXTRA_OECMAKE += " \
    -DSECO_LIBS_DIR=${STAGING_DIR_TARGET}${libdir} \
    -DZLIB_DIR=${STAGING_DIR_TARGET}${libdir} \
    -DECC_SRC=${STAGING_DIR_TARGET}${libdir} \
"

do_install:append() {
    install -d  ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/seco_nvm_daemon.service ${D}${systemd_system_unitdir}
}

COMPATIBLE_MACHINE = "(mx8dxl-nxp-bsp)"
