#!/bin/sh

echo \
"""
abi <abi/4.0>,
include <tunables/global>
profile bitbake /**/bitbake/bin/bitbake flags=(unconfined) {
userns,
}
""" > /etc/apparmor.d/bitbake

apparmor_parser -r /etc/apparmor.d/bitbake

