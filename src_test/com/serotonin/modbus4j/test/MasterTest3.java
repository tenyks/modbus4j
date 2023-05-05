/*
    Copyright (C) 2006-2007 Serotonin Software Technologies Inc.
 	@author Matthew Lohbihler
 */
package com.serotonin.modbus4j.test;

import com.serotonin.modbus4j.BatchRead;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.ip.IpParameters;
import com.serotonin.modbus4j.locator.BaseLocator;
import com.serotonin.modbus4j.msg.ModbusRequest;
import com.serotonin.modbus4j.msg.ReadHoldingRegistersRequest;
import com.serotonin.modbus4j.msg.ReadHoldingRegistersResponse;
import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

/**
 * @author Matthew Lohbihler
 */
public class MasterTest3 {
    public static void main(String[] args) throws Exception {
        IpParameters ipParameters = new IpParameters();
        ipParameters.setHost("192.168.137.1");
        // ipParameters.setHost("99.247.60.96");
        // ipParameters.setHost("193.109.41.121");
        // ipParameters.setHost("141.211.194.29");
        ipParameters.setPort(502);
        ipParameters.setEncapsulated(false);

        ModbusFactory modbusFactory = new ModbusFactory();
        // ModbusMaster master = modbusFactory.createTcpMaster(ipParameters, true);
        ModbusMaster master = modbusFactory.createTcpMaster(ipParameters, false);
        master.setTimeout(4000);
        master.setRetries(1);


        BatchRead<Integer> batch = new BatchRead<Integer>();
        //        batch.addLocator(0, new ModbusLocator(1, RegisterRange.COIL_STATUS, 2048, DataType.BINARY));
        //        batch.addLocator(1, new ModbusLocator(1, RegisterRange.COIL_STATUS, 2049, DataType.BINARY));
        //        batch.addLocator(2, new ModbusLocator(1, RegisterRange.COIL_STATUS, 2050, DataType.BINARY));
        //        batch.addLocator(3, new ModbusLocator(1, RegisterRange.COIL_STATUS, 3584, DataType.BINARY));
        //        batch.addLocator(4, new ModbusLocator(1, RegisterRange.COIL_STATUS, 3585, DataType.BINARY));
        //        batch.addLocator(5, new ModbusLocator(1, RegisterRange.COIL_STATUS, 3648, DataType.BINARY));
        //        batch.addLocator(6, new ModbusLocator(1, RegisterRange.COIL_STATUS, 3649, DataType.BINARY));
        //        batch.addLocator(7, new ModbusLocator(1, RegisterRange.COIL_STATUS, 3650, DataType.BINARY));
        //        batch.addLocator(8, new ModbusLocator(1, RegisterRange.COIL_STATUS, 3651, DataType.BINARY));
        //        batch.addLocator(9, new ModbusLocator(1, RegisterRange.COIL_STATUS, 3652, DataType.BINARY));
        //        batch.addLocator(10, new ModbusLocator(1, RegisterRange.COIL_STATUS, 3653, DataType.BINARY));
        //        batch.addLocator(11, new ModbusLocator(1, RegisterRange.COIL_STATUS, 3654, DataType.BINARY));
        //        batch.addLocator(12, new ModbusLocator(1, RegisterRange.COIL_STATUS, 3661, DataType.BINARY));
        //        batch.addLocator(13, new ModbusLocator(1, RegisterRange.COIL_STATUS, 3662, DataType.BINARY));
        //        batch.addLocator(15, new ModbusLocator(1, RegisterRange.COIL_STATUS, 3665, DataType.BINARY));
        //        batch.addLocator(16, new ModbusLocator(1, RegisterRange.COIL_STATUS, 3668, DataType.BINARY));
        //        batch.addLocator(18, new ModbusLocator(1, RegisterRange.COIL_STATUS, 3969, DataType.BINARY));

        batch.addLocator(0, BaseLocator.holdingRegister(1, 100, DataType.TWO_BYTE_INT_SIGNED));
//        batch.addLocator(1, BaseLocator.holdingRegister(1, 2, DataType.TWO_BYTE_INT_SIGNED));

        ModbusRequest req1 = new ReadHoldingRegistersRequest(2, 0, 1);

        try {
            master.init();

            while (true) {
                batch.setContiguousRequests(false);
//                BatchResults<Integer> results = master.send(batch);
//                System.out.println(results.getValue(0));
//                System.out.println(results.getValue(1));

                ReadHoldingRegistersResponse response = (ReadHoldingRegistersResponse)master.send(req1);
                System.out.println(HexBin.encode(response.getData()));

                Thread.sleep(2000);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            master.destroy();
        }
    }
}
