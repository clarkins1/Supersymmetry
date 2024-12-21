FBR = recipemap('fixed_bed_reactor')

FBR.recipeBuilder()
    .notConsumable(ore('catalystBedSupportedNickel'))
    .fluidInputs(fluid('naphthalene') * 1000)
    .fluidInputs(fluid('hydrogen') * 4000)
    .fluidOutputs(fluid('tetralin') * 1000)
    .duration(40)
    .EUt(Globals.voltAmps[2])
    .buildAndRegister()


