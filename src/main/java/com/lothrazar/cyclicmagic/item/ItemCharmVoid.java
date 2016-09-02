package com.lothrazar.cyclicmagic.item;
import com.lothrazar.cyclicmagic.util.UtilEntity;
import com.lothrazar.cyclicmagic.util.UtilParticle;
import com.lothrazar.cyclicmagic.util.UtilSound;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemCharmVoid extends BaseCharm {
  private static final int durability = 16;
  public ItemCharmVoid() {
    super(durability);
  }

  /**
   * Called each tick as long the item is on a player inventory. Uses by maps to
   * check if is on a player hand and update it's contents.
   */
  public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
    if (entityIn instanceof EntityPlayer) {
      EntityPlayer living = (EntityPlayer) entityIn;
      if (living.getPosition().getY() < -30) {
        UtilEntity.teleportWallSafe(living, worldIn, new BlockPos(living.getPosition().getX(), 255, living.getPosition().getZ()));
        super.damageCharm(living,stack,itemSlot);
      
        UtilSound.playSound(living, worldIn.getSpawnPoint(), SoundEvents.ENTITY_ENDERMEN_TELEPORT, living.getSoundCategory());
        UtilParticle.spawnParticle(worldIn, EnumParticleTypes.PORTAL, living.getPosition());
       
      }
    }
  }
}
