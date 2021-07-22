package me.offeex.ofx.client.module.modules.render;

import com.google.common.eventbus.Subscribe;
import me.offeex.ofx.api.event.events.PacketEvent;
import me.offeex.ofx.client.module.Module;
import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;
import org.lwjgl.glfw.GLFW;

public class NightTime extends Module {
    public NightTime() {
        super("Night Time", "Changes world time to night", GLFW.GLFW_KEY_UNKNOWN, Category.RENDER, false);
    }

    private long oldTime = 0;

    @Override
    public void onEnable() {
        oldTime = mc.world.getTime();
    }

    @Override
    public void onTick() {
        mc.world.setTimeOfDay(18000);
    }

    @Override
    public void onDisable() {
        mc.world.setTimeOfDay(oldTime);
    }

    // Fixed by @author https://github.com/fuckyouthinkimboogieman on 22 July of 2021
    @Subscribe
    public void onPacket(PacketEvent.Receive event) {
        if (event.getPacket() instanceof WorldTimeUpdateS2CPacket) {
            event.cancel();
        }
    }
}