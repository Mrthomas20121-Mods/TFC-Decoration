const resources = require('./resources')
const path = require('path')

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
	'mossy_bricks',
	'cracked_bricks',
	'mud_bricks',
    'sandstone',
    'mud_pillar',
    'pillar',
    'sandstone_pillar',
    'raw_mud'
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

// for(let rock_type of ROCK_TYPES) {
//     for(let block_type of FULLBLOCK_TYPES) {
//         switch (block_type) {
//             case 'sandstone':
//                 resources.model.block.cube_bottom_top([`tfc_decoration:blocks/stonetypes/${block_type}/side/${rock_type}`, `tfc_decoration:blocks/stonetypes/${block_type}/bottom/${rock_type}`, `tfc_decoration:blocks/stonetypes/${block_type}/top/${rock_type}`], )
//                 break;
//             default:
//                 resources.model.block.cube_all(`tfc_decoration:blocks/stonetypes/${block_type}`)
//                 break;
//         }
//     }
// }

for(let wood_type of WOOD_TYPES) {
    new resources.model.block.LadderBlockModel(`tfc_decoration:blocks/wood/ladder/${wood_type}`).build(['wood', 'ladder', wood_type+'.json'])
    resources.model.item.ItemGenerated(`tfc_decoration:blocks/wood/ladder/${wood_type}`,'wood', 'ladder', wood_type+'.json')
}