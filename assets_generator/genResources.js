const fs = require('fs')
const path = require('path')
const resources = require('./resources_2')

const tfc_decoration_path = path.join('..', 'src', 'main', 'resources', 'assets', 'tfc_decoration')
const tfc_path = path.join('..', 'src', 'main', 'resources', 'assets', 'tfc')
const lang_path = path.join(tfc_decoration_path, 'lang')

const rock_types = [
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

const rock_parts = [
    'pillar',
    'mossy_cobble',
	'mossy_bricks',
	'cracked_bricks',
    'sandstone',
    'sandstone_pillar',
    'raw_mud',
    'mud_bricks',
    'mud_pillar',
    'rockwool'
]

const wood_types = [
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

const wood_parts = [
    'fence_log',
    'ladder'
]

resources.translateAll(lang_path, rock_types, rock_parts, wood_types, wood_parts, true)

for(let rock_type of rock_types) {
    resources.dyeRecipe(tfc_decoration_path, `rockwool/${rock_type}.json`, resources.item(`tfc:raw/${rock_type}`), resources.item('tfc:crop/product/burlap_cloth'), `tfc_decoration:rockwool/${rock_type}`)
}

for(let wood_type of wood_types) {
    resources.shapedRecipe(tfc_decoration_path, `ladder/${wood_type}.json`, ['XXX', ' X ', 'XXX'], {
        'X': resources.item(`tfc:wood/lumber/${wood_type}`)
    }, `tfc_decoration:wood/ladder/${wood_type}`, 3)
}