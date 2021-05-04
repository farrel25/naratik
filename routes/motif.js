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
/* GET motif name and task ID*/
router.get('/', async(req, res) => {
    // Save decoded url to variable ( http://host/motif?id=xxxxxxxxxxxxx )
    const url = decodeURIComponent(fullUrl(req));
    // Search parameter from URL
    const current_url = new URL(url);
    const search_params = current_url.searchParams;
    const id = search_params.get('id');
    // Search for file with given id in collection
    const allData = []
    try {
        await db.collection('motif')
            // Search document based on unique ID of the classification task
            .doc(id)
            // Get the value inside that document
            .get()
            // Store the value on allData[]
            .then((value) => {
                allData.push(value.data())
            })
        // Response with motif and unique ID
        res.json(allData[0])
    }catch (e) {
        res.send("ID not found")
    }
        });


module.exports = router;