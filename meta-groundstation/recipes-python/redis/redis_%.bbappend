
DEPENDS += " redistimeseries"

RDEPENDS:${PN} += " redistimeseries"

do_install:append() {
    CONF=${D}${sysconfdir}/redis/redis.conf
    grep -q 'redistimeseries.so' $CONF || echo "loadmodule /usr/lib/redis/modules/redistimeseries.so" >> $CONF
}
