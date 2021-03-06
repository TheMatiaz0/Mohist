package org.bukkit.craftbukkit.v1_16_R3.entity;

import com.google.common.base.Preconditions;
import net.minecraft.entity.passive.PigEntity;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.CraftServer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;

public class CraftPig extends CraftAnimals implements Pig {

    public CraftPig(CraftServer server, PigEntity entity) {
        super(server, entity);
    }

    @Override
    public boolean hasSaddle() {
        return getHandle().isHorseSaddled();
    }

    @Override
    public void setSaddle(boolean saddled) {
        getHandle().field_234214_bx_.setSaddledFromBoolean(saddled);
    }

    @Override
    public int getBoostTicks() {
        return getHandle().field_234214_bx_.saddledRaw ? getHandle().field_234214_bx_.boostTimeRaw : 0;
    }

    @Override
    public void setBoostTicks(int ticks) {
        Preconditions.checkArgument(ticks >= 0, "ticks must be >= 0");

        getHandle().field_234214_bx_.setBoostTicks(ticks);
    }

    @Override
    public int getCurrentBoostTicks() {
        return getHandle().field_234214_bx_.saddledRaw ? getHandle().field_234214_bx_.field_233611_b_ : 0;
    }

    @Override
    public void setCurrentBoostTicks(int ticks) {
        if (!getHandle().field_234214_bx_.saddledRaw) {
            return;
        }

        int max = getHandle().field_234214_bx_.boostTimeRaw;
        Preconditions.checkArgument(ticks >= 0 && ticks <= max, "boost ticks must not exceed 0 or %d (inclusive)", max);

        this.getHandle().field_234214_bx_.field_233611_b_ = ticks;
    }

    @Override
    public Material getSteerMaterial() {
        return Material.CARROT_ON_A_STICK;
    }

    @Override
    public PigEntity getHandle() {
        return (PigEntity) entity;
    }

    @Override
    public String toString() {
        return "CraftPig";
    }

    @Override
    public EntityType getType() {
        return EntityType.PIG;
    }
}
