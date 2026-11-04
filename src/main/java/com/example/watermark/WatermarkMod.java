package com.example.watermark;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = WatermarkMod.MODID, name = WatermarkMod.NAME, version = WatermarkMod.VERSION)
public class WatermarkMod {
    public static final String MODID = "watermarkmod";
    public static final String NAME = "Google Watermark";
    public static final String VERSION = "1.0";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        if (event.getSide().isClient()) {
            new ClientProxy().init(event);
        }
    }
  }
