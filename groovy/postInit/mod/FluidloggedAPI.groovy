import git.jbredwards.fluidlogged_api.api.event.FluidloggableEvent
import net.minecraftforge.fluids.FluidRegistry

event_manager.listen { FluidloggableEvent event ->
    if (event.fluid == FluidRegistry.LAVA) {
        return false
    }
}