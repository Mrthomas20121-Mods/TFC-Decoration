let ROCK_TYPES = [
    'granite',
    'diorite',
    'gabbro',
    'shale',
    'claystone',
    'rocksalt',
    'limestone',
    'conglomerate',
    'dolomite',
    'chert',
    'chalk',
    'rhyolite',
    'basalt',
    'andesite',
    'dacite',
    'quartzite',
    'slate',
    'phyllite',
    'schist',
    'gneiss',
    'marble',
	'blaimorite',
    'boninite',
    'carbonatite',
    'foidolite',
    'arkose',
    'jaspillite',
    'travertine',
    'wackestone',
    'blueschist',
    'greenschist',
    'cataclasite',
    'mylonite'
]

let FULLBLOCK_TYPES = [
    'mossy_cobble',
	'mossy_brick',
	'cracked_brick',
	'wet_mud',
	'mud'
]

function capitalizeFirstLetter(str) {
    return str.charAt(0).toUpperCase() + str.slice(1);
  }

let output = ""

for(let type of ROCK_TYPES)
{
    output+=`# ${type}`
    output+=`\ntile.tfc_decoration.mossy_cobble.${type}.name=Moss ${capitalizeFirstLetter(type)}`
    output+=`\ntile.tfc_decoration.mossy_brick.${type}.name=Mossy ${capitalizeFirstLetter(type)} Bricks`
    output+=`\ntile.tfc_decoration.cracked_brick.${type}.name=Cracked ${capitalizeFirstLetter(type)} Bricks`
    output+=`\ntile.tfc_decoration.wet_mud.${type}.name=Wet ${capitalizeFirstLetter(type)} Mud`
    output+=`\ntile.tfc_decoration.mud.${type}.name=${capitalizeFirstLetter(type)} Mud`
    output+=`\n`
}


console.log(output)
