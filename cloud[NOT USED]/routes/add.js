const express = require('express');
const router = express.Router();
const fire = require("./fire");
const db = fire.firestore();


/*
    This HTTP POST needs JSON as its request body
    format:
    {
    "id" : <unique ID>,
    "motif1":{
        "motifName": <Motif Name 1>,
        "value": <Prediction Value of Motif1>
            },
    "motif2":{
        "motifName": <Motif Name 2>,
        "value": <Prediction Value of Motif2>
            },
    "motif3":{
        "motifName": <Motif Name 3>,
        "value": <Prediction Value of Motif3
            }
    }
 */

router.post('/', async (req,res)=>{
    const {id,motif0,motif1,motif2,motif3,motif4} = req.body

    try{
        // Upload data to firestore
        await db.collection('motif')
            .doc(id)
            .set(
                {   motif0,
                    motif1,
                    motif2,
                    motif3,
                    motif4
            })
        res.send("Upload Success")
    }catch (e) {
        console.log(e)
        res.send("Upload Fail")
    }
})

module.exports = router;
