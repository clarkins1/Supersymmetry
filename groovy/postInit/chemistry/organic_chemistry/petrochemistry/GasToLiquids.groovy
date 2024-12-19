import globals.Globals

REACTION_FURNACE = recipemap('reaction_furnace')
ELECTROMAGNETIC_SEPARATOR = recipemap('electromagnetic_separator')
PSA = recipemap('pressure_swing_adsorption')
PYROLYSE = recipemap('pyrolyse_oven')
BCR = recipemap('bubble_column_reactor')

// Syngas production and refining

    // Methane reforming

    REACTION_FURNACE.recipeBuilder()
        .fluidInputs(fluid('hot_hp_methane') * 1000)
        .fluidInputs(fluid('dense_steam') * 1000)
        .notConsumable(ore('catalystBedSupportedNickel'))
        .fluidOutputs(fluid('raw_hydrogen_rich_syngas') * 7000)
        .duration(160)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

    REACTION_FURNACE.recipeBuilder()
        .fluidInputs(fluid('hot_hp_natural_gas') * 1500)
        .fluidInputs(fluid('dense_steam') * 1000)
        .notConsumable(ore('catalystBedSupportedNickel'))
        .fluidOutputs(fluid('raw_hydrogen_rich_syngas') * 7000)
        .duration(160)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

    // Purification

    SIFTER.recipeBuilder()
        .inputs(ore('dustMolecularSieve'))
        .fluidInputs(fluid('raw_hydrogen_rich_syngas') * 12000)
        .fluidOutputs(fluid('hydrogen_rich_syngas') * 12000)
        .outputs(metaitem('dustMethaneMolecularSieve'))
        .duration(4)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

    // WGSR

    REACTION_FURNACE.recipeBuilder()
        .fluidInputs(fluid('hydrocarbon_syngas') * 7000)
        .fluidInputs(fluid('dense_steam') * 1000)
        .notConsumable(ore('catalystBedLtsCatalyst'))
        .fluidOutputs(fluid('syngas') * 9000)
        .duration(320)
        .EUt(30)
        .buildAndRegister()

    REACTION_FURNACE.recipeBuilder()
        .fluidInputs(fluid('syngas') * 7000)
        .fluidInputs(fluid('dense_steam') * 1000)
        .notConsumable(metaitem('dustHtsCatalyst'))
        .fluidOutputs(fluid('syngas') * 9000)
        .duration(160)
        .EUt(120)
        .buildAndRegister()

    // Purification

    PSA.recipeBuilder()
        .fluidInputs(fluid('reformed_syngas') * 9000)
        .notConsumable(metaitem('dustMolecularSieve') * 5)
        .fluidOutputs(fluid('hydrogen') * 8000)
        .fluidOutputs(fluid('carbon_dioxide') * 1000)
        .duration(160)
        .EUt(120)
        .buildAndRegister()

    PSA.recipeBuilder()
        .fluidInputs(fluid('syngas') * 7000)
        .notConsumable(metaitem('dustMolecularSieve') * 5)
        .fluidOutputs(fluid('hydrogen') * 6000)
        .fluidOutputs(fluid('carbon_monoxide') * 1000)
        .duration(160)
        .EUt(120)
        .buildAndRegister()

// Syngas utilization

    // LTFT

    BCR.recipeBuilder()