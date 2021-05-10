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
            // Check if data fetched successfully
            try{
                // This will trigger error if data not fetched successfully
                console.log(allData[0]["motif_1"])
                // This will send response with valid data if data fetched successfully
                res.json(allData[0])
            }catch{
                // Send response if data not fetched successfully
                res.send("Please try again later")
            }
        } catch (e) {
            // Will triggered if URL is invalid
            res.send("Invalid URL")
        }
    });


module.exports = router;
