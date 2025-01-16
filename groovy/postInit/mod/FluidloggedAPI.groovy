import git.jbredwards.fluidlogged_api.api.event.FluidloggableEvent
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fml.common.eventhandler.Event

event_manager.listen{FluidloggableEvent event ->
    if (event.fluid == FluidRegistry.LAVA) {
        event.setResult(Event.Result.DENY)
    }
}