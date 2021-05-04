var express = require('express');
var router = express.Router();
const url = require('url');

// Function to get full url
function fullUrl(req){
    return url.format({
        protocol: req.protocol,
        host:req.get('host'),
        pathname: req.url
    });
}
/* GET motif name*/
router.get('/', function(req, res, next) {
    // Save decoded url to variable
    var url = decodeURIComponent(fullUrl(req));
    // Search parameter from URL
    const current_url = new URL(url);
    var search_params = current_url.searchParams;
    const id = search_params.get('id');
    // Return id from URL parameter
    res.send(id);
});

module.exports = router;