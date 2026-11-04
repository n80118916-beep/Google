package com.example.watermark;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy {
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new WatermarkHUD());
    }
}
