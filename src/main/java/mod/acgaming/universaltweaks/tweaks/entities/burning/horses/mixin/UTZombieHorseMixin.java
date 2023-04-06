package mod.acgaming.universaltweaks.tweaks.entities.burning.horses.mixin;

import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityZombieHorse;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import mod.acgaming.universaltweaks.config.UTConfig;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityZombieHorse.class)
public abstract class UTZombieHorseMixin extends AbstractHorse
{
    public UTZombieHorseMixin(World worldIn)
    {
        super(worldIn);
    }

    @Override
    public void onLivingUpdate()
    {
        if (UTConfig.TWEAKS_ENTITIES.utBurningUndeadHorsesToggle && !this.isTame() && this.world.isDaytime() && !this.world.isRemote)
        {
            float f = this.getBrightness();
            if (f > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.world.canSeeSky(new BlockPos(this.posX, this.posY + (double) this.getEyeHeight(), this.posZ)))
            {
                this.setFire(8);
            }
        }
        super.onLivingUpdate();
    }
}