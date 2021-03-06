package org.opennms.netmgt.nb;

import org.opennms.core.utils.InetAddressUtils;
import org.opennms.netmgt.model.OnmsNode;

public class Nms4005NetworkBuilder extends TestNetworkBuilder {

    static {
        R1_IP_IF_MAP.put(InetAddressUtils.addr("10.1.2.1"), 1);
        R1_IP_IF_MAP.put(InetAddressUtils.addr("10.1.3.1"), 2);
        R1_IP_IF_MAP.put(InetAddressUtils.addr("10.1.1.2"), 3);
        R1_IF_NETMASK_MAP.put(1, InetAddressUtils.addr("255.255.255.0"));
        R1_IF_NETMASK_MAP.put(2, InetAddressUtils.addr("255.255.255.0"));
        R1_IF_NETMASK_MAP.put(3, InetAddressUtils.addr("255.255.255.0"));
        R2_IP_IF_MAP.put(InetAddressUtils.addr("10.1.2.2"), 1);
        R2_IP_IF_MAP.put(InetAddressUtils.addr("10.1.5.1"), 2);
        R2_IF_NETMASK_MAP.put(1, InetAddressUtils.addr("255.255.255.0"));
        R2_IF_NETMASK_MAP.put(2, InetAddressUtils.addr("255.255.255.0"));
        R3_IP_IF_MAP.put(InetAddressUtils.addr("10.1.3.2"), 1);
        R3_IP_IF_MAP.put(InetAddressUtils.addr("10.1.4.1"), 2);
        R3_IP_IF_MAP.put(InetAddressUtils.addr("10.1.5.2"), 3);
        R3_IF_NETMASK_MAP.put(1, InetAddressUtils.addr("255.255.255.0"));
        R3_IF_NETMASK_MAP.put(2, InetAddressUtils.addr("255.255.255.0"));
        R3_IF_NETMASK_MAP.put(3, InetAddressUtils.addr("255.255.255.0"));
        R4_IP_IF_MAP.put(InetAddressUtils.addr("10.1.4.2"), 1);
        R4_IF_NETMASK_MAP.put(1, InetAddressUtils.addr("255.255.255.0"));

    	R1_IF_IFNAME_MAP.put(1, "Fa0/0");
    	R1_IF_IFDESCR_MAP.put(1, "FastEthernet0/0");
    	R1_IF_IFNAME_MAP.put(2, "Fa0/1");
    	R1_IF_IFDESCR_MAP.put(2, "FastEthernet0/1");
    	R1_IF_IFNAME_MAP.put(3, "Fa1/0");
    	R1_IF_IFDESCR_MAP.put(3, "FastEthernet1/0");
    	R1_IF_IFNAME_MAP.put(4, "Nu0");
    	R1_IF_IFDESCR_MAP.put(4, "Null0");
    	R2_IF_IFNAME_MAP.put(1, "Fa0/0");
    	R2_IF_IFNAME_MAP.put(2, "Fa0/1");
    	R2_IF_IFDESCR_MAP.put(2, "FastEthernet0/1");
    	R2_IF_IFDESCR_MAP.put(1, "FastEthernet0/0");
    	R2_IF_IFNAME_MAP.put(3, "Nu0");
    	R2_IF_IFDESCR_MAP.put(3, "Null0");
    	R3_IF_IFNAME_MAP.put(1, "Fa0/0");
    	R3_IF_IFDESCR_MAP.put(1, "FastEthernet0/0");
    	R3_IF_IFNAME_MAP.put(2, "Fa0/1");
    	R3_IF_IFDESCR_MAP.put(2, "FastEthernet0/1");
    	R3_IF_IFNAME_MAP.put(3, "Fa1/0");
    	R3_IF_IFDESCR_MAP.put(3, "FastEthernet1/0");
    	R3_IF_IFNAME_MAP.put(4, "Nu0");
    	R3_IF_IFDESCR_MAP.put(4, "Null0");
    	R4_IF_IFNAME_MAP.put(1, "Fa0/0");
    	R4_IF_IFDESCR_MAP.put(1, "FastEthernet0/0");
    	R4_IF_IFNAME_MAP.put(2, "Fa0/1");
    	R4_IF_IFDESCR_MAP.put(2, "FastEthernet0/1");
    	R4_IF_IFNAME_MAP.put(3, "Nu0");
    	R4_IF_IFDESCR_MAP.put(3, "Null0");
    }

    public OnmsNode getR1() {
        return getNode(R1_NAME,R1_SYSOID,R1_IP,R1_IP_IF_MAP,R1_IF_IFNAME_MAP,R1_IF_MAC_MAP,R1_IF_IFDESCR_MAP,R1_IF_IFALIAS_MAP,R1_IF_NETMASK_MAP);
    }    

    public OnmsNode getR2() {
        return getNode(R2_NAME,R1_SYSOID,R2_IP,R2_IP_IF_MAP,R2_IF_IFNAME_MAP,R2_IF_MAC_MAP,R2_IF_IFDESCR_MAP,R2_IF_IFALIAS_MAP,R2_IF_NETMASK_MAP);
    }    

    public OnmsNode getR3() {
        return getNode(R3_NAME,R3_SYSOID,R3_IP,R3_IP_IF_MAP,R3_IF_IFNAME_MAP,R3_IF_MAC_MAP,R3_IF_IFDESCR_MAP,R3_IF_IFALIAS_MAP,R3_IF_NETMASK_MAP);
    }    

    public OnmsNode getR4() {
        return getNode(R4_NAME,R4_SYSOID,R4_IP,R4_IP_IF_MAP,R4_IF_IFNAME_MAP,R4_IF_MAC_MAP,R4_IF_IFDESCR_MAP,R4_IF_IFALIAS_MAP,R4_IF_NETMASK_MAP);
    }    

}
