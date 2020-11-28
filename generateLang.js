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
	'wet_mud',
	'mud'
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
    output+=`# ${type}`
    output+=lang(['tile', 'tfc_decoration', 'mossy_cobble', type, 'name'], ['moss', type])
    output+=lang(['tile', 'tfc_decoration', 'mossy_brick', type, 'name'], ['mossy', type, 'bricks'])
    output+=lang(['tile', 'tfc_decoration', 'cracked_brick', type, 'name'], ['cracked', type, 'bricks'])
    output+=lang(['tile', 'tfc_decoration', 'wet_mud', type, 'name'], ['wet', type, 'mud'])
    output+=lang(['tile', 'tfc_decoration', 'mud', type, 'name'], [type, 'mud'])
    output+=`\n`
}
output+=lang(['tile', 'tfc_decoration', 'mud_bricks'], ['mud', 'bricks'])
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
    elements.forEach((s, index, array) => array[index] = capitalizeFirstLetter(s))
    return `\n${entry}=${elements.join(' ')}`
}