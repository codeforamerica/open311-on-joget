<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;
    key=ABQIAAAAImhJhTtTM8LB3dZ_vtAGKxT2yXp_ZAY8_ufC3CFXhHIE1NvwkxTxM2i4OyKToYMzrfRLbTsOI_lW1A" type="text/javascript"></script>
<script type="text/javascript">

function load1(){
var map = new GMap(document.getElementById("map"));
var lat1 = parseFloat("-89.947729");
var longi1 = parseFloat("38.781521");
var point = new GPoint(lat1,longi1);
var marker = new GMarker(point);
map.addControl(new GLargeMapControl());
map.addControl(new GMapTypeControl());
map.centerAndZoom(point, 3);
GEvent.addListener(marker, "click", function() {
marker.openInfoWindowHtml("<table width='215'><tr><td><a target='_blank' href='http://www.lcls.org/'>Lewis & Clark Library System</a></td></tr><tr><td><img src='http://www.lcls.org/images/galleries/tour/01-BuildingFromLot.JPG' border='0' width='195px' height='95' /></td></tr><tr><td>425 Goshen Road<br />Edwardsville,IL 62025<br />618-656-3216</td></tr></table><br /><a target='_blank' href='http://maps.google.com/maps?q=425 Goshen Road%20Edwardsville,%20IL'>Directions</a>");
});
map.addOverlay(marker);
}
 </script>

<div id="${elementParamName!}" style="width: 900px; height: 500px"  value="${key}"></div>
<input type="button" onClick="load1();" value="Click ME"></input>


