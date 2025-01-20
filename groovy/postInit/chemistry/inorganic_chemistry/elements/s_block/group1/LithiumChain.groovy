import globals.Globals
import static globals.SinteringGlobals.*

BR = recipemap('batch_reactor')
FLUIDIZEDBR = recipemap('fluidized_bed_reactor')
DISTILLATION_TOWER = recipemap('distillation_tower')
DISTILLERY = recipemap('distillery')
ROASTER = recipemap('roaster')
MIXER = recipemap('mixer')
CENTRIFUGE = recipemap('centrifuge')
AUTOCLAVE = recipemap('autoclave')
ELECTROLYTIC_CELL = recipemap('electrolytic_cell')
FLOTATION = recipemap('froth_flotation')
CRYSTALLIZER = recipemap('crystallizer')
ROTARY_KILN = recipemap("rotary_kiln")
DRYER = recipemap("dryer")
MACERATOR = recipemap("macerator")
CLARIFIER = recipemap("clarifier")
VACUUM_CHAMBER = recipemap('vacuum_chamber')

//UNIVERSAL
MIXER.recipeBuilder()
        .inputs(ore('dustImpureSpodumene') * 8)
        .fluidInputs(fluid('distilled_water') * 2000)
        .fluidOutputs(fluid('impure_spodumene_slurry') * 2000)
        .EUt(Globals.voltAmps[3])
        .duration(80)
        .buildAndRegister()

FLOTATION.recipeBuilder()
        .fluidInputs(fluid('impure_spodumene_slurry') * 2000)
        .notConsumable(fluid('alkaline_sodium_oleate_solution') * 1000)
        .fluidOutputs(fluid('spodumene_slurry') * 1000)
        .fluidOutputs(fluid('pegmatite_tailing_slurry') * 1000)
        .EUt(Globals.voltAmps[3])
        .duration(80)
        .buildAndRegister()

CLARIFIER.recipeBuilder()
        .fluidInputs(fluid('spodumene_slurry') * 1000)
        .outputs(metaitem('dustSpodumene') * 16)
        .fluidOutputs(fluid('wastewater') * 1000)
        .EUt(Globals.voltAmps[1])
        .duration(20)
        .buildAndRegister()

CENTRIFUGE.recipeBuilder()
        .fluidInputs(fluid('pegmatite_tailing_slurry') * 1000)
        .outputs(metaitem('dustPegmatiteTailings') * 2)
        .fluidOutputs(fluid('wastewater') * 1000)
        .EUt(Globals.voltAmps[1])
        .duration(20)
        .buildAndRegister()

ROASTER.recipeBuilder()
        .inputs(ore('dustSpodumene') * 1)
        .outputs(metaitem('dustBetaSpodumene') * 8)
        .EUt(30)
        .duration(200)
        .buildAndRegister()

ROASTER.recipeBuilder()
        .inputs(ore('dustPetalite') * 1)
        .outputs(metaitem('dustRoastedPetalite') * 12)
        .EUt(30)
        .duration(200)
        .buildAndRegister()

FLUIDIZEDBR.recipeBuilder()
        .inputs(ore('dustSpodumene') * 1)
        .outputs(metaitem('dustBetaSpodumene') * 10)
        .EUt(120)
        .duration(40)
        .buildAndRegister()

FLUIDIZEDBR.recipeBuilder()
        .inputs(ore('dustPetalite') * 1)
        .outputs(metaitem('dustRoastedPetalite') * 16)
        .EUt(160)
        .duration(40)
        .buildAndRegister()

CENTRIFUGE.recipeBuilder()
        .inputs(ore('dustRoastedPetalite') * 16)
        .outputs(metaitem('dustBetaSpodumene') * 10)
        .outputs(metaitem('dustSiliconDioxide') * 6)
        .EUt(30)
        .duration(40)
        .buildAndRegister()

//ACID DIGESTION (90%)
BR.recipeBuilder()
        .fluidInputs(fluid('sulfuric_acid') * 2000)
        .inputs(ore('dustBetaSpodumene') * 20)
        .fluidOutputs(fluid('impure_lithium_sulfate_solution') * 2000)
        .outputs(metaitem('dustAluminiumSilicate') * 8)
        .EUt(120)
        .duration(200)
        .buildAndRegister()

//ALUMINIUM SILICATE PROCESSING
AUTOCLAVE.recipeBuilder()
        .fluidInputs(fluid('sodium_hydroxide_solution') * 2000)
        .inputs(ore('dustAluminiumSilicate') * 8)
        .fluidOutputs(fluid('sodium_silicate_solution') * 1000)
        .outputs(metaitem('dustAlumina') * 5)
        .EUt(120)
        .duration(200)
        .buildAndRegister()

BR.recipeBuilder()
        .fluidInputs(fluid('sodium_silicate_solution') * 1000)
        .fluidInputs(fluid('hydrogen_chloride') * 2000)
        .outputs(metaitem('dustWetSilicaGel') * 3)
        .fluidOutputs(fluid('salt_water') * 2000)
        .EUt(120)
        .duration(200)
        .buildAndRegister()

DRYER.recipeBuilder()
        .inputs(ore('dustWetSilicaGel'))
        .outputs(metaitem('dustSilicaGel'))
        .EUt(30)
        .duration(100)
        .buildAndRegister()

MACERATOR.recipeBuilder()
        .inputs(ore('dustSilicaGel'))
        .outputs(metaitem('dustSiliconDioxide'))
        .EUt(30)
        .duration(80)
        .buildAndRegister()

//IMPURITY PRECIPITATION
BR.recipeBuilder()
        .inputs(ore('dustSodiumHydroxide'))
        .fluidInputs(fluid('impure_lithium_sulfate_solution') * 1000)
        .chancedOutput(metaitem('dustAluminiumHydroxide'), 1111, 0)
        .chancedOutput(metaitem('dustIronIiiHydroxide'), 1111, 0)
        .chancedOutput(metaitem('dustMagnesiumHydroxide'), 1667, 0)
        .fluidOutputs(fluid('lithium_sulfate_solution') * 900)
        .EUt(120)
        .duration(200)
        .buildAndRegister()

BR.recipeBuilder()
        .fluidInputs(fluid('lithium_sulfate_solution') * 1000)
        .inputs(ore('dustSodaAsh') * 6)
        .outputs(metaitem('gregtechfoodoption:lithium_carbonate_dust') * 6)
        .fluidOutputs(fluid('sodium_sulfate_solution') * 1000)
        .duration(180)
        .EUt(200)
        .buildAndRegister()

ROASTER.recipeBuilder()
        .fluidInputs(fluid('hydrochloric_acid') * 2000)
        .inputs(ore('dustLithiumCarbonate') * 6)
        .outputs(metaitem('dustLithiumChloride') * 4)
        .fluidOutputs(fluid('dense_steam') * 3000)
        .fluidOutputs(fluid('carbon_dioxide') * 1000)
        .duration(180)
        .EUt(200)
        .buildAndRegister()

ELECTROLYTIC_CELL.recipeBuilder()
        .notConsumable(metaitem('graphite_electrode'))
        .notConsumable(metaitem('stickSteel'))
        .notConsumable(fluid('rock_salt') * 358)
        .fluidInputs(fluid('lithium_chloride') * 288)
        .fluidOutputs(fluid('chlorine') * 1000)
        .outputs(metaitem('dustLithium'))
        .EUt(120)
        .duration(180)
        .buildAndRegister()

ROASTER.recipeBuilder()
        .fluidInputs(fluid('distilled_water') * 1000)
        .inputs(ore('dustAmblygonite') * 8)
        .outputs(metaitem('dustAluminiumPhosphate') * 6)
        .fluidOutputs(fluid('amblygonite_leach') * 1000)
        .duration(120)
        .EUt(200)
        .buildAndRegister()

BR.recipeBuilder()
        .fluidInputs(fluid('amblygonite_leach') * 2000)
        .fluidInputs(fluid('sulfuric_acid') * 1000)
        .outputs(metaitem('dustLithiumSulfate') * 7)
        .fluidOutputs(fluid('diluted_hydrofluoric_acid') * 2000)
        .duration(60)
        .EUt(200)
        .buildAndRegister()

DISTILLATION_TOWER.recipeBuilder()
        .fluidInputs(fluid('diluted_hydrofluoric_acid') * 2000)
        .fluidOutputs(fluid('hydrofluoric_acid') * 1000)
        .fluidOutputs(fluid('water') * 1000)
        .duration(120)
        .EUt(200)
        .buildAndRegister()

BR.recipeBuilder()
        .inputs(ore('dustLithiumSulfate') * 7)
        .fluidInputs(fluid('soda_ash_solution') * 1000)
        .outputs(metaitem('gregtechfoodoption:lithium_carbonate_dust') * 6)
        .fluidOutputs(fluid('sodium_sulfate_solution') * 1000)
        .duration(120)
        .EUt(200)
        .buildAndRegister()

BR.recipeBuilder()
        .inputs(ore('dustAluminiumPhosphate') * 6)
        .fluidInputs(fluid('hydrogen_chloride') * 3000)
        .outputs(metaitem('dustAluminiumChloride') * 3)
        .fluidOutputs(fluid('phosphoric_acid') * 1000)
        .duration(120)
        .EUt(200)
        .buildAndRegister()

CRYSTALLIZER.recipeBuilder()
        .inputs(ore('dustLepidolite') * 1)
        .fluidInputs(fluid('sulfuric_acid') * 5000)
        .fluidOutputs(fluid('lepidolite_leach') * 2000)
        .outputs(metaitem('dustAlkaliAlumMix') * 12)
        .duration(120)
        .EUt(200)
        .buildAndRegister()

CRYSTALLIZER.recipeBuilder()
        .inputs(ore('dustQuicklime') * 2)
        .fluidInputs(fluid('lepidolite_leach') * 2000)
        .outputs(metaitem('dustCalciumSulfate') * 6)
        .fluidOutputs(fluid('neutralized_lepidolite_leach') * 3000)
        .duration(120)
        .EUt(200)
        .buildAndRegister()

CRYSTALLIZER.recipeBuilder()
        .notConsumable(metaitem('dustTinyAluminiumSulfate'))
        .fluidInputs(fluid('neutralized_lepidolite_leach') * 3000)
        .outputs(metaitem('dustAluminiumSulfate') * 3)
        .fluidOutputs(fluid('aluminium_free_lepidolite_leach') * 3000)
        .duration(120)
        .EUt(200)
        .buildAndRegister()

CRYSTALLIZER.recipeBuilder()
        .inputs(ore('dustSodaAsh') * 6)
        .fluidInputs(fluid('aluminium_free_lepidolite_leach') * 1000)
        .outputs(metaitem('gregtechfoodoption:lithium_carbonate_dust') * 6)
        .fluidOutputs(fluid('wastewater') * 1000)
        .duration(120)
        .EUt(200)
        .buildAndRegister()

// Lithium hydroxide
BR.recipeBuilder()
        .fluidInputs(fluid('water') * 8000)
        .inputs(metaitem('gregtechfoodoption:lithium_carbonate_dust') * 6)
        .inputs(metaitem('dustCalciumHydroxide') * 5)
        .outputs(metaitem('dustCalcite') * 5)
        .fluidOutputs(fluid('lithium_hydroxide_mother_liquor') * 8000)
        .duration(480)
        .EUt(30)
        .buildAndRegister();

CRYSTALLIZER.recipeBuilder()
        .fluidInputs(fluid('lithium_hydroxide_mother_liquor') * 8000)
        .fluidOutputs(fluid('water') * 7000)
        .outputs(metaitem('dustLithiumHydroxideMonohydrate') * 6)
        .duration(240)
        .EUt(30)
        .buildAndRegister();

ROASTER.recipeBuilder()
        .inputs(metaitem('dustLithiumHydroxideMonohydrate') * 6)
        .fluidOutputs(fluid('dense_steam') * 1000)
        .outputs(metaitem('dustLithiumHydroxide') * 3)
        .duration(960)
        .EUt(30)
        .buildAndRegister();

VACUUM_CHAMBER.recipeBuilder()
        .inputs(metaitem('dustLithiumHydroxideMonohydrate') * 6)
        .fluidOutputs(fluid('dense_steam') * 1000)
        .outputs(metaitem('dustLithiumHydroxide') * 3)
        .duration(60)
        .EUt(60)
        .buildAndRegister();
// LiNO3

BR.recipeBuilder()
        .inputs(ore('dustLithiumCarbonate') * 6)
        .fluidInputs(fluid('nitric_acid') * 2000)
        .fluidOutputs(fluid('lithium_nitrate_solution') * 1000)
        .fluidOutputs(fluid('carbon_dioxide') * 1000)
        .EUt(30)
        .duration(80)
        .buildAndRegister()

DISTILLERY.recipeBuilder()
        .fluidInputs(fluid('lithium_nitrate_solution') * 1000)
        .outputs(metaitem('dustLithiumNitrate') * 10)
        .fluidOutputs(fluid('water') * 1000)
        .EUt(30)
        .duration(80)
        .buildAndRegister()
