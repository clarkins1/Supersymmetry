import globals.Globals

ROASTER = recipemap('roaster')
ELECTROLYTIC_CELL = recipemap('electrolytic_cell')
BR = recipemap('batch_reactor')

//TRONA
ROASTER.recipeBuilder()
        .inputs(ore('dustTrona') * 28)
        .outputs(metaitem('dustSodaAsh') * 18)
        .fluidOutputs(fluid('dense_steam') * 5000)
        .fluidOutputs(fluid('carbon_dioxide') * 1000)
        .EUt(30)
        .duration(120)
        .buildAndRegister()

ROASTER.recipeBuilder()
        .inputs(ore('dustSodaAsh') * 6)
        .inputs(ore('dustAnyPurityCarbon') * 2)
        .outputs(metaitem('dustSodium') * 2)
        .fluidOutputs(fluid('carbon_monoxide') * 3000)
        .EUt(30)
        .duration(120)
        .buildAndRegister()

//IMPURE HALITE PURIFICATION
BR.recipeBuilder()
        .inputs(ore('dustImpureSalt') * 2)
        .fluidInputs(fluid('distilled_water') * 1000)
        .fluidOutputs(fluid('halite_leach') * 1000)
        .EUt(30)
        .duration(20)
        .buildAndRegister()

BR.recipeBuilder()
        .inputs(ore('dustSodaAsh'))
        .fluidInputs(fluid('halite_leach') * 8000)
        .chancedOutput(metaitem('dustBarite') * 5, 1667, 0)
        .outputs(metaitem('dustClay'))
        .fluidOutputs(fluid('salt_water') * 8000)
        .EUt(30)
        .duration(160)
        .buildAndRegister()

//CASTNER PROCESS
ELECTROLYTIC_CELL.recipeBuilder()
        .fluidInputs(fluid('sodium_hydroxide') * 432)
        .notConsumable(metaitem('stickNickel'))
        .notConsumable(metaitem('stickIron'))
        .outputs(metaitem('dustSodium'))
        .fluidOutputs(fluid('oxygen') * 1000)
        .fluidOutputs(fluid('hydrogen') * 1000)
        .duration(200)
        .EUt(Globals.voltAmps[1] * 2)
        .buildAndRegister()

//DOWNS CELL PROCESSES
ELECTROLYZER.recipeBuilder()
        .notConsumable(metaitem('stickNickel'))
        .notConsumable(metaitem('stickIron'))
        .fluidInputs(fluid('salt') * 288)
        .fluidOutputs(fluid('chlorine') * 1000)
        .outputs(metaitem('dustSodium'))
        .EUt(30)
        .duration(300)
        .buildAndRegister()

//COMPOUNDS
DISTILLERY.recipeBuilder()
        .fluidInputs(fluid('sodium_nitrate_solution') * 1000)
        .outputs(metaitem('dustSodiumNitrate') * 5)
        .fluidOutputs(fluid('water') * 1000)
        .duration(140)
        .EUt(30)
        .buildAndRegister()

ROASTER.recipeBuilder()
        .inputs(ore('dustSodiumNitrate') * 5)
        .outputs(metaitem('dustSodiumNitrite') * 4)
        .fluidOutputs(fluid('oxygen') * 1000)
        .duration(400)
        .EUt(30)
        .buildAndRegister()

BR.recipeBuilder()
        .fluidInputs(fluid('sodium_nitrate') * 720)
        .fluidInputs(fluid('lead') * 144)
        .fluidOutputs(fluid('sodium_nitrite') * 576)
        .outputs(metaitem('dustLeadOxide') * 2)
        .duration(20)
        .EUt(30)
        .buildAndRegister()

ROASTER.recipeBuilder()
        .inputs(ore('dustSodiumNitrite') * 8)
        .outputs(metaitem('dustSodiumOxide') * 3)
        .fluidOutputs(fluid('nitric_oxide') * 1000)
        .fluidOutputs(fluid('nitrogen_dioxide') * 1000)
        .duration(60)
        .EUt(30)
        .buildAndRegister()

BR.recipeBuilder()
        .inputs(ore('dustSodiumOxide') * 3)
        .fluidInputs(fluid('water') * 3000)
        .fluidOutputs(fluid('sodium_hydroxide_solution') * 2000)
        .duration(60)
        .EUt(30)
        .buildAndRegister()