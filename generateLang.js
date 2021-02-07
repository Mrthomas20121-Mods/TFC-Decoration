const fs = require('fs')

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
	'mud_brick',
	'sandstone',
	'pillar',
]

let WOOD_TYPES = [
    'ash',
    'aspen',
    'birch',
    'chestnut',
    'douglas_fir',
    'hickory',
    'maple',
    'oak',
    'pine',
    'sequoia',
    'spruce',
    'sycamore',
    'white_cedar',
    'willow',
    'kapok',
    'acacia',
    'rosewood',
    'blackwood',
    'palm',
    'hevea'
]

function capitalizeFirstLetter(str) {
    return str.charAt(0).toUpperCase() + str.slice(1);
  }

let output = ""

for(let type of ROCK_TYPES)
{
    output+=lang(['config', 'tfc_decoration', 'worldgen'], ['Enable/Disable Worldgen'])
    output+='\n'
    output+=`# ${type}`
    output+=lang(['tile', 'tfc_decoration', 'mossy_cobble', type, 'name'], ['moss', type])
    output+=lang(['tile', 'tfc_decoration', 'mossy_bricks', type, 'name'], ['mossy', type, 'bricks'])
    output+=lang(['tile', 'tfc_decoration', 'cracked_bricks', type, 'name'], ['cracked', type, 'bricks'])
	output+=lang(['tile', 'tfc_decoration', 'raw_mud', type, 'name'], [type, 'raw_mud'])
    output+=lang(['tile', 'tfc_decoration', 'mud_bricks', type, 'name'], [type, 'mud_bricks'])
    output+=lang(['tile', 'tfc_decoration', 'mud_pillar', type, 'name'], [type, 'mud_pillar'])
    output+=lang(['tile', 'tfc_decoration', 'sandstone', type, 'name'], [type, 'sandstone'])
	output+=lang(['tile', 'tfc_decoration', 'pillar', type, 'name'], [type, 'pillar'])
	output+=lang(['tile', 'tfc_decoration', 'sandstone_pillar', type, 'name'], [type, 'sandstone_pillar'])
    output+=`\n`
    // items
    output+=lang(['item', 'tfc_decoration', 'mud_brick', type, 'name'], [type, 'mud_brick'])
    output+=lang(['item', 'tfc_decoration', 'mud_ball', type, 'name'], [type, 'mud_ball'])
    output+=`\n`
}
output+=`\n`

for(let type of WOOD_TYPES)
{
    output+=lang(['tile', 'tfc_decoration', 'wood', 'fence_log', type, 'name'], [type, 'fence', 'log'])
}

fs.writeFileSync('./en_us.lang', output, 'utf8')

/**
 * create a lang entry
 * @param {String[]} parts 
 * @param {String[]} elements
 * @returns {String}
 */
function lang(parts, elements) {
    let entry = parts.join('.')
    elements.forEach((value, index, array) => {
        if(value.includes('_'))
        {
            let arr = value.split('_')
            arr.forEach((val, index, array) => array[index] = capitalizeFirstLetter(val))
            //console.log(arr)
            array[index] = arr.join(' ')
        }
        else array[index] = capitalizeFirstLetter(value)
    })
    return `\n${entry}=${elements.join(' ')}`
}