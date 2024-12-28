SUMMARY = "Scientific Graphics Library for Python"
HOMEPAGE = "https://www.pyqtgraph.org"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=f3e5f0e3bfada0ee09266e5402b0a690"

SRC_URI = "https://files.pythonhosted.org/packages/source/p/pyqtgraph/pyqtgraph-0.12.4.tar.gz"
SRC_URI[sha256sum] = "c2c8f2b7e1d7f97a710f370acff6368d686c9d6cdc560f2d83dcf59185dfa19d"

inherit setuptools3 python3native

DEPENDS += "python3 python3-pyqt5 python3-numpy"

RDEPENDS:${PN} += "python3-core python3-numpy python3-pyqt5"

# Optional: Specify PYPI_PACKAGE for better compatibility
PYPI_PACKAGE = "pyqtgraph"