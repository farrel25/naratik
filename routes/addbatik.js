const express = require('express');
const router = express.Router();
const fire = require("./fire");
const db = fire.firestore();
const url = require('url');

// Function to get full url
function fullUrl(req){
    return url.format({
        protocol: req.protocol,
        host:req.get('host'),
        pathname: req.url
    });
}

/* GET home page. */
router.get('/', async(req, res) => {
    // Save decoded url to variable
    const url = decodeURIComponent(fullUrl(req));
    // Search parameter from URL
    const current_url = new URL(url);
    const search_params = current_url.searchParams;
    const id = search_params.get('id');
    const motif_1 = search_params.get('motif_1');
    const motif_2 = search_params.get('motif_2');
    const motif_3 = search_params.get('motif_3');
    const value_1 = search_params.get('value_1');
    const value_2 = search_params.get('value_2');
    const value_3 = search_params.get('value_3');
    try{
        await db.collection('motif')
            .doc(id)
            .set({
                motif_1: motif_1,
                motif_2: motif_2,
                motif_3: motif_3,
                value_1: value_1,
                value_2: value_2,
                value_3: value_3
            })
            .then(()=>{
                res.send("Upload Success")
            })
    }catch (e) {
        res.send("Upload Fail")
    }
});

module.exports = router;
