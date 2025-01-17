package net.createcobblestone.blocks;

import com.jozufozu.flywheel.api.Instancer;
import com.jozufozu.flywheel.api.MaterialManager;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityInstance;
import com.simibubi.create.content.kinetics.base.flwdata.RotatingData;
import com.simibubi.create.content.kinetics.simpleRelays.ShaftBlock;
import net.minecraft.world.level.block.state.BlockState;

public class MechanicalGeneratorInstance extends KineticBlockEntityInstance<MechanicalGeneratorBlockEntity> {

    protected RotatingData rotatingModel1;
    public MechanicalGeneratorInstance(MaterialManager materialManager, MechanicalGeneratorBlockEntity blockEntity) {
        super(materialManager, blockEntity);
    }

    public void init() {
        this.rotatingModel1 = this.setup(this.getModel().createInstance());

        rotatingModel1.setRotationAxis(axis)
                .setRotationalSpeed(getBlockEntitySpeed())
                .setRotationOffset(-getRotationOffset(axis))
                .setColor(blockEntity)
                .setPosition(getInstancePosition());
    }

    public void update() {
        this.updateRotation(this.rotatingModel1);
    }

    public void updateLight() {
        this.relight(this.pos, this.rotatingModel1);
    }

    public void remove() {
        this.rotatingModel1.delete();
    }

    protected BlockState getRenderedBlockState() {
        return AllBlocks.SHAFT.getDefaultState().setValue(ShaftBlock.AXIS, blockState.getValue(MechanicalGeneratorBlock.HORIZONTAL_FACING).getAxis());
    }

    protected Instancer<RotatingData> getModel() {
        return this.getRotatingMaterial().getModel(this.getRenderedBlockState());
    }
}
