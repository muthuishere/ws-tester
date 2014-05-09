{section:type=SOURCE|title=main|id=rssection_main|height=300}
{pre}
<%
REQUEST.setAttribute("pid", PROBLEMID);
%>
{/pre}
{pre}
<style>
.x-mask, .x-mask.x-mask-gray
{
	zoom: 1;
	filter: alpha(opacity=100);
	opacity: 1.0;
        background-color: BECD19;
}
table.padded td 
{ 
	padding:10px; 
}
table.notPadded td 
{ 
	padding:2px; 
}

#wrapper {
	width: 1240px;
        text-align: left;
	//margin: 0 auto;
}


/* Header
-----------------------------------------------------------------------------*/
#header {
	height: 163px;
	//background: #FFE680;
	background-image: url(/resolve/custom/images/Resolve_header.jpg); 
}
#logoutButton {
	position: relative;
	top: 53px;
	left: 1058px;
}


/* Middle
-----------------------------------------------------------------------------*/
#content {
	width: 100%;
	//min-height: 600px;
	//_height: 600px;
}
.steps {
	height: 41px;
        width: 110px;
        position: relative;
        right: 3px;
}
.sectionLabel {
        font-family: Helvetica;
        font-size: 14px;
        font-weight: bold;
}
.continue {
        position: relative;
        top: 40px;
        left: 750px;
}
.back {
        position: relative;
        top: 40px;
        right: 130px;
}
.backTodIndoorsSignal {
        position: relative;
        top: 40px;
        right: 130px;
}
.backIssue {
        position: relative;
        top: 40px;
        right: 130px;
}
.backLocation {
        position: relative;
        top: 40px;
        right: 130px;
}
.submit {
        position: relative;
        top: 40px;
        left: 750px;
}


#msisdnPane {
	background:#F5F4F0;
	height: 35px;
	border-style: solid;
	border-width: 1px;
	border-color: #E9E8E6;
}

.historyPane {
	background:#F5F4F0;
	min-height: 30px;
	border-style: solid;
	border-width: 1px;
	border-color: #E9E8E6;
        font-family: Helvetica;
        font-size: 12px;
}


.mainInput {
        font-family: Helvetica;
        font-size: 14px;
        color: #666666;
        border: 2px solid #dadada;
        border-radius: 5px;
}
.mainInputLabel {
        font-family: Helvetica;
        font-size: 14px;
}
.ext-mb-text{
      font-family: Helvetica;
      font-size: 12px;
    }
.x-window-header-text{
      font-family: Helvetica;
      font-size: 12px;
    }

/* Validation
-----------------------------------------------------------------------------*/
.mainInput:focus
{
    outline: none;
    border-color: #9ecaed;
    box-shadow: 0 0 10px #9ecaed;
}

.invalidInput
{
    outline: none;
    border-color: #FF80AA;
    box-shadow: 0 0 10px #FF80AA;
}
</style>
<script type="text/javascript">
var lucidNext = "";
var breadcrumb = [];
var imgBreadcrumb = [];
var position = 0;
breadcrumb.push('#issueDeviceSection');
imgBreadcrumb.push('step1');
function prepareContent()
{
      if(Ext.get('urlMsisdn') != null) {
          if(Ext.get('urlMsisdn').dom.innerHTML != 'NA') {
            document.getElementById('msisdn').value = Ext.get('urlMsisdn').dom.innerHTML;
            document.getElementById('msisdn').readOnly = true;
          }
          else
          {
            document.getElementById('msisdn').value = '447';
            document.getElementById('msisdn').readOnly = false;
          }
      }
      if(Ext.get('urlImsi') != null) {
            document.getElementById('imsi').value = Ext.get('urlImsi').dom.innerHTML;
      }
      if(Ext.get('urlBan') != null) {
            document.getElementById('ban').value = Ext.get('urlBan').dom.innerHTML;
      }
      if(Ext.get('urlUser') != null) {
            document.getElementById('user').value = Ext.get('urlUser').dom.innerHTML;
      }
      if(Ext.get('urlImei1') != null) {
            document.getElementById('imei1').value = Ext.get('urlImei1').dom.innerHTML;
      }
      if(Ext.get('urlImei2') != null) {
            document.getElementById('imei2').value = Ext.get('urlImei2').dom.innerHTML;
      }
      if(Ext.get('urlImei3') != null) {
            document.getElementById('imei3').value = Ext.get('urlImei3').dom.innerHTML;
      }
      if(Ext.get('manufacturer1') != null){
            document.getElementById('psManufacturer1').value = Ext.get('manufacturer1').dom.innerHTML;
            document.getElementById('psTac1').value = Ext.get('tac1').dom.innerHTML;
      }
      else
      {
            var el = Ext.select("#psDevice1Row");
            el.setVisibilityMode(Ext.Element.DISPLAY);
            el.hide();

      }
      if(Ext.get('manufacturer2') != null) {
            Ext.get('psManufacturer2').set({value: Ext.get('manufacturer2').dom.innerHTML});
            document.getElementById('psTac2').value = Ext.get('tac2').dom.innerHTML;
      }
      else
      {
            var el = Ext.select("#psDevice2Row");
            el.setVisibilityMode(Ext.Element.DISPLAY);
            el.hide();

      }
      if(Ext.get('manufacturer3') != null) {
            Ext.get('psManufacturer3').set({value: Ext.get('manufacturer3').dom.innerHTML});
            document.getElementById('psTac3').value = Ext.get('tac3').dom.innerHTML;
      }
      else
      {
            var el = Ext.select("#psDevice3Row");
            el.setVisibilityMode(Ext.Element.DISPLAY);
            el.hide();

      }
      if(Ext.get('manufacturerTds') != null) {
            Ext.get('tdsManufacturer').set({value: Ext.get('manufacturerTds').dom.innerHTML});
            document.getElementById('tdsTac').value = Ext.get('tacTds').dom.innerHTML;
      }
      else
      {
            var el = Ext.select("#tdsDeviceRow");
            el.setVisibilityMode(Ext.Element.DISPLAY);
            el.hide();

      }
      if(Ext.get('model1') != null) {
            Ext.get('psModel1').set({value: Ext.get('model1').dom.innerHTML});
      }
      if(Ext.get('model2') != null) {
            Ext.get('psModel2').set({value: Ext.get('model2').dom.innerHTML});
      }
      if(Ext.get('model3') != null) {
            Ext.get('psModel3').set({value: Ext.get('model3').dom.innerHTML});
      }
      if(Ext.get('modelTds') != null) {
            Ext.get('tdsModel').set({value: Ext.get('modelTds').dom.innerHTML});
      }

      var psManufacturer1 = document.getElementById('psManufacturer1').value;
      var psManufacturer2 = document.getElementById('psManufacturer2').value;
      var psManufacturer3 = document.getElementById('psManufacturer3').value;
      var tdsManufacturer = document.getElementById('tdsManufacturer').value;
      var psModel1 = document.getElementById('psModel1').value;
      var psModel2 = document.getElementById('psModel2').value;
      var psModel3 = document.getElementById('psModel3').value;
      var tdsModel = document.getElementById('tdsModel').value;

      var radio = document.getElementsByName('DEVICE');

      if(psManufacturer1 == "Not Found" || psManufacturer1 == "Bad TAC"  || psManufacturer1 == ""  || psManufacturer1 == null || psManufacturer1 == "Unavailable")
      {
         radio[0].disabled = true;
      }
      if(psManufacturer2 == "Not Found" || psManufacturer2 == "Bad TAC"  || psManufacturer2 == ""  || psManufacturer2 == null || psManufacturer2 == "Unavailable")
      {
         radio[1].disabled = true;
      }
      if(psManufacturer3 == "Not Found" || psManufacturer3 == "Bad TAC"  || psManufacturer3 == ""  || psManufacturer3 == null || psManufacturer3 == "Unavailable")
      {
         radio[2].disabled = true;
      }
      if(tdsManufacturer == "Not Found" || tdsManufacturer == "Bad TAC"  || tdsManufacturer == ""  || tdsManufacturer == null || tdsManufacturer == "Unavailable")
      {
         radio[3].disabled = true;
      }

      var elContinue = Ext.get("continueLinkTodIndoorsSignal");
      elContinue.setVisibilityMode(Ext.Element.DISPLAY);
      elContinue.hide();

      var elSubmitIssue = Ext.get("submitLinkIssue");
      elSubmitIssue.setVisibilityMode(Ext.Element.DISPLAY);
      elSubmitIssue.hide();

      var elSubmitLocation = Ext.get("submitLinkLocation");
      elSubmitLocation.setVisibilityMode(Ext.Element.DISPLAY);
      elSubmitLocation.hide();
}
function currentGMTDateTime()
{
    var now = new Date();
    var nowUtc = now.getTime();
    var tz = now.getTimezoneOffset() * 60000;
    var nowOffset = nowUtc + tz;

    //var jan = new Date(now.getFullYear(), 0, 1);
    //var jul = new Date(now.getFullYear(), 6, 1);
    //var dstOffset = Math.max(jan.getTimezoneOffset(), jul.getTimezoneOffset());

    //if(tz < dstOffset)
    //{
    //   nowOffset = nowOffset + 3600000;
    //}

    return new Date(nowOffset);
}
</script>
<script type="text/javascript">
Ext.MyJsonString="";
Ext.Manufacturers;
Ext.Models;


Ext.onReady(function() {
function checkIfRunbookFinishedByResultTag()
{
   var a=document.getElementsByName("isDoneYet");
   if(!(a.length>0&&a.item(0).value=="STOP_REFRESH")){return false}else{return true}
} 
function timedCount()
{
   var done = checkIfRunbookFinishedByResultTag();
   if(! done)
   {
       t=setTimeout(timedCount,1000);
   }
   else
   {
      prepareContent();
      Ext.select('#issueDeviceSection').show();
      loadMask.hide();
   }
}

//var pId = Ext.get('pid').dom.innerHTML;
//Ext.get('problemId').set({value: pId});

var mousewheelevt=(/Firefox/i.test(navigator.userAgent))? "DOMMouseScroll" : "mousewheel"
if (document.attachEvent)
    document.attachEvent("on"+mousewheelevt, function(e){if (!e) var e = window.event; e.returnValue = false; e.cancelBubble = true; return false;})
else if (document.addEventListener)
    document.addEventListener(mousewheelevt, function(e){if (!e) var e = window.event; e.returnValue = false; e.cancelBubble = true; return false;}, false)



  var lastOccurredDate = new Ext.form.field.Date({
			    fieldLabel: 'Date',
                            labelPad: 2,
                            format: 'd/m/Y',
                            fieldCls: 'mainInput',
                            maskRe: /[\d\/]/i,
                            regex: /^([0-2][0-9]|3[0-1])\/(0[0-9]|1[0-2])\/([1-2][0-2][0-9][0-9])$/i,
                            invalidText: 'The date must be entered in the following format dd/mm/yyyy, where dd is a valid day of the month, mm is a valid month of the year, and yyyy is a valid year',
                            maxValue: currentGMTDateTime(),
                            minValue: Ext.Date.add(currentGMTDateTime(), Ext.Date.DAY, -90),
                            value: currentGMTDateTime(),
			    width: 100,
                            editable: true,
			    name: 'LASTOCCURREDDATE',
			    hideLabel: true,
			    id: 'lastOccurredDate',
			    renderTo : 'lastOccurredDateDiv',
                            listeners: {
                                        select: function(field, value, eOpts) {
                                                    var today = Ext.Date.format(currentGMTDateTime(),'d/m/Y');
                                                    var strDate = Ext.Date.format(value,'d/m/Y');
                                                    if(strDate == today)
                                                    {
                                                       var now = currentGMTDateTime();
                                                       lastOccurredTime.setMaxValue(now);
                                                    }
                                                    else
                                                    {
                                                       lastOccurredTime.setMaxValue("23:59");
                                                    }
                                                }
                                       }
			});

      var lastOccurredTime = new Ext.form.field.Time({
			    fieldLabel:'Time',
                            hideLabel: true,
                            format: 'H:i',
                            fieldCls: 'mainInput',
                            minValue: '00:00',
                            maxValue: currentGMTDateTime(),
                            value: currentGMTDateTime(),
                            maskRe: /[\d\s:]/i,
                            regex: /^([0-1][0-9]|2[0-3]):([0-5][0-9])$/i,
                            invalidText: 'The time must be entered in the following format hh:mm where hh must be between 00 and 23, and mm must be between 00 and 59.',
                            increment: 30,
                            width: 100,
                            editable: true,
                            id: 'lastOccurredTime',
			    name: 'LASTOCCURREDTIME',
                            renderTo : 'lastOccurredTimeDiv',
                            labelPad: 2,
                            listeners: {
                                expand: function(o) {
                                    if( this.isValid() ) {
                                        this.getPicker().getSelectionModel().suspendEvents(false);
                                        this.getPicker().getSelectionModel().deselectAll(true);
                                        this.getPicker().clearHighlight();
                                        this.getPicker().getSelectionModel().resumeEvents();
                                    }
                                }
                            }
			});

var els = Ext.select(".section");
els.setVisibilityMode(Ext.Element.DISPLAY);
els.hide();
var resolveHeader = Ext.select('#xwikinav_header');
resolveHeader.setVisibilityMode(Ext.Element.DISPLAY);
resolveHeader.hide();
var loadMask = new Ext.LoadMask(Ext.getBody(), {msg:"Loading dynamic page content....Please Wait"});
loadMask.on('show', function(btn, e){
timedCount();
});
loadMask.show();

    Ext.override(Ext.data.JsonReader, {
	read: function(response) 
        {
		var pattern = /<div id="AjaxResultDiv">(.*?)<\/div>/;
		var matches = response.responseText.match(pattern);
		
		var o = eval("("+matches[1]+")");
		if(!o) {
			throw {message: "CFJsonReader.read: Json object not found"};
		}
		if(o.metaData){
			delete this.ef;
			this.meta = o.metaData;
			this.recordType = Ext.data.Record.create(o.metaData.fields);
			this.onMetaChange(this.meta, this.recordType, o);
		}
		return this.readRecords(o);
	}
    });


    Ext.define("ListManufacturers", {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'manufacturer'}
        ]
    });
	
	Ext.define("ListModels", {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'model'},
            {name: 'tac'}
        ]
    });

    var dsManufacturers = Ext.create('Ext.data.Store', {
        model: 'ListManufacturers',
        proxy: {
            type: 'ajax',
            url: '/resolve/service/wiki/view/LTE/GetManufacturers',
            reader: {
                type: 'json',
                root: 'data'
            }
        },
        pageSize: 10
    });
	
	    var dsModels = Ext.create('Ext.data.Store', {
        model: 'ListModels',
        proxy: {
            type: 'ajax',
            url: '/resolve/service/wiki/view/LTE/GetModels',
            reader: {
                type: 'json',
                root: 'data'
            }
        },
        pageSize: 10
    });

    var manufacturerCombo = Ext.create('Ext.form.field.ComboBox', {
        fieldLabel: 'Manufacturer',
        labelStyle: 'font-family:Helvetica; font-size:14px;',
        fieldStyle: 'font-family:Helvetica; font-size:14px; color:#666666',
        fieldCls: 'mainInput',
        id: 'manufacturerCombo',
        name: 'MANUALMANUFACTURER',
        renderTo: Ext.get("manufacturerSelect"),
        displayField: 'manufacturer',
        width: 300,
        store: dsManufacturers,
        minChars: 2,
        forceSelection : true,
        typeAhead: false,
        hideLabel: false,
        hideTrigger:true,
        pageSize: 0,
        emptyText: 'Type and select...',   
        listConfig: {
            loadingText: 'Searching...',
            emptyText: 'Manufacturer not found. Please select a valid manufacturer from the list'
        },
        // override default onSelect
        listeners: {
            select: function(combo, record) 
            {
                 var selected = combo.getValue();
                 if(selected == "Manufacturer not recognised. Please select a valid manufacturer from the list")
                 {
                    combo.clearValue();
                 }
            }
        }
    });
	
	
	var modelCombo = Ext.create('Ext.form.field.ComboBox', {
        fieldLabel: 'Model',
        labelStyle: 'font-family:Helvetica; font-size:14px;',
        fieldStyle: 'font-family:Helvetica; font-size:14px; color:#666666',
        fieldCls: 'mainInput',
        name: 'MANUALMODEL',
        id: 'modelCombo',
        renderTo: Ext.get("modelSelect"),
        displayField: 'model',
        width: 300,
        store: dsModels,
        minChars: 2,
        typeAhead: false,
        hideLabel: false,
        hideTrigger:true,
        forceSelection : false,
        pageSize: 0,
        emptyText: 'Type and select...',   
        listConfig: {
            loadingText: 'Searching...',
            emptyText: 'Model not recognised. If correct please continue, otherwise select a valid Model'
        },
        // override default onSelect
        listeners: {
            select: function(combo, record) {
                 document.getElementById('manualTac').value = record[0].get('tac');
                 var selected = combo.getValue();
                 if(selected == "Model not recognised. If correct please continue, otherwise select a valid Model")
                 {
                    combo.clearValue();
                 }
            }
        }
    });

    var houseNumberText = Ext.create('Ext.form.field.Text', {
        fieldStyle: 'font-family:Helvetica; font-size:14px; color:#666666',
        fieldCls: 'mainInput',
        id: 'houseNumberText',
        name: 'HOUSENUMBER',
        renderTo: Ext.get("houseNumber"),
        width: 150,
        hideLabel: true,
        emptyText: 'House Number'
    });

    var houseNameText = Ext.create('Ext.form.field.Text', {
        fieldStyle: 'font-family:Helvetica; font-size:14px; color:#666666',
        fieldCls: 'mainInput',
        id: 'houseNameText',
        name: 'HOUSENAME',
        renderTo: Ext.get("houseName"),
        width: 150,
        hideLabel: true,
        emptyText: 'House Name'
    });

    var streetText = Ext.create('Ext.form.field.Text', {
        fieldStyle: 'font-family:Helvetica; font-size:14px; color:#666666',
        fieldCls: 'mainInput',
        id: 'streetText',
        name: 'STREET',
        renderTo: Ext.get("street"),
        width: 305,
        hideLabel: true,
        emptyText: 'Street'
    });

    var townText = Ext.create('Ext.form.field.Text', {
        fieldStyle: 'font-family:Helvetica; font-size:14px; color:#666666',
        fieldCls: 'mainInput',
        id: 'townText',
        name: 'TOWN',
        renderTo: Ext.get("town"),
        width: 305,
        hideLabel: true,
        emptyText: 'Town'
    });

    var countyText = Ext.create('Ext.form.field.Text', {
        fieldStyle: 'font-family:Helvetica; font-size:14px; color:#666666',
        fieldCls: 'mainInput',
        id: 'countyText',
        name: 'COUNTY',
        renderTo: Ext.get("county"),
        width: 305,
        hideLabel: true,
        emptyText: 'County'
    });

    var postcodeText = Ext.create('Ext.form.field.Text', {
        fieldStyle: 'font-family:Helvetica; font-size:14px; color:#666666',
        fieldCls: 'mainInput',
        id: 'postcodeText',
        name: 'PARTIALADDRPOSTCODE',
        renderTo: Ext.get("addrPostcode"),
        width: 150,
        hideLabel: true,
        emptyText: 'Postcode',
        hidden: true
    });

});
function showHistoryDetail(event, el)
{
     var id = el.id + "img";
     var src = document.getElementById(id).src;
     if(src.indexOf("history_open_button") != -1)
     {
        document.getElementById(id).src = "/resolve/custom/images/history_close_button.jpg";
     }
     else
     {
        document.getElementById(id).src = "/resolve/custom/images/history_open_button.jpg";
     }
     var detailId = "#" + el.id + "detail";
     var els = Ext.select(detailId);
     els.setVisibilityMode(Ext.Element.DISPLAY);
     els.toggle();
     return false;
}
</script>
{/pre}
<form id="dataEntryForm" name="entryForm" action="$wiki.action.execute">
<input type="hidden" name="action" value="EXECUTEPROCESS"/>
<input id="problemId" type="hidden" name="PROBLEMID" value="NEW" />
<input type="hidden" name="wiki" value="LTE.VoiceDiagnostic" />
<table width="100%"><tr><td align="center">
<div id="wrapper">
	<div id="header">
		<!--<a id="logoutButton" href="/resolve/service/logout"><img src="/resolve/custom/images/logout_button.jpg" alt="Logout"/></a>-->
	</div><!-- #header-->
<table width="100%" style="position: relative; left: 30px;">
<tr>
<td width="240px" valign="top">
				<table id="msisdnPane" width="220px;"><tr>
								<td style="font-family:Helvetica;font-weight:bold;font-size:12px;">MSISDN:</td>
								<td>
                                                                    <input id="msisdn" class="mainInput" type="text" name="MSISDN" style="width:130px;" value="447" maxlength="12" onkeypress="return isNumberKey(event)" style="font-family:Helvetica;font-size:12px;"/>
                                                                    <input id="imsi" type="hidden" name="IMSI" value="">
                                                                    <input id="ban" type="hidden" name="BAN" value="">
                                                                    <input id="user" type="hidden" name="USER" value="">
                        </td>
					</tr>
				</table>			
                        <BR>
			<p style="font-family:Helvetica;font-weight:bold;font-size:12pt;">Voice Diagnostic History.</p><BR>
                                {detail}
                                display diagnostic history
                                {detail}
</td>
<td valign="top">
				<img id="step1" class="steps" src="/resolve/custom/images/Step1_on.png" alt="step1" style="position:relative;left:0;top:0;"/>
				<img id="step2" class="steps" src="/resolve/custom/images/Step2_off.png" alt="step2"/>
				<img id="step3" class="steps" src="/resolve/custom/images/Step3_off.png" alt="step3"/>
				<img id="step4" class="steps" src="/resolve/custom/images/Step4_off.png" alt="step4"/>
				<img id="step5" class="steps" src="/resolve/custom/images/Step5_off.png" alt="step5"/>
				<img id="step6" class="steps" src="/resolve/custom/images/Step6_off.png" alt="step6"/>
<BR><BR><BR>
<div class="section" id="issueDeviceSection" style="display:none">
<span class="sectionLabel">Which of the following best describes the problem being experienced:</span>
<BR><BR>
<select id="issueReported" class="mainInput" name="ISSUE" style="width:450px;" onfocus="selectReset(this)" onchange="issueSelectionChanged(this)">
<option value="NONE">Select response...</option>
<option value="MC">Cannot make calls</option>
<option value="RC">Cannot receive calls</option>
<option value="MRC">Cannot make or receive calls</option>
<option value="DC">Dropped calls</option>
<option value="SQ">Speech quality</option>
<option value="NB">Network busy / 3 rising beeps</option>
<option value="ECO">Emergency calls only</option>
<option value="NS">No Signal</option>
<option value="NNR">Number not recognised</option>
<option value="CSN">Cannot make calls to specific number</option>
<option value="RSN">Cannot receive calls from specific number</option>
<option value="MIC">Cannot make international calls</option>
<option value="RIC">Cannot receive international calls in the UK</option>
<option value="CPN">Cannot call premium number</option>
<option value="MRCR">Cannot make or receive calls while roaming abroad</option>
<option value="MCR">Cannot make calls while roaming abroad</option>
<option value="RCR">Cannot receive calls while roaming abroad</option>
<option value="DRT">Make calls - delayed ring tone</option>
<option value="DRTSN">Make calls - delayed ring tone specific number</option>
<option value="O">Other</option>
</select>
<BR><BR><BR><BR><BR<BR>
<p class="sectionLabel">Select a device to use in diagnostics:</p><BR>
      <table class="padded">
        <tr id="psDevice1Row">
          <td align="left">
          <input id="imei1" type="hidden" name="IMEI1" value="" /> 
          <input id="device1" type="radio" name="DEVICE" value="1" /> 
          <span class="mainInputLabel">&nbsp;&nbsp;&nbsp;Peoplesoft Handset:</span></td>
          <td>
          <input id="psManufacturer1" class="mainInput" type="text" name="PSMAN1" style="width:150px;" value=""
          readonly="readonly" /><input id="psTac1" type="hidden" name="TAC1" value=""/>
          <input id="psModel1" class="mainInput" type="text" name="PSMOD1" style="width:150px;" value=""
          readonly="readonly" /></td>
        </tr>
        <tr id="psDevice2Row">
          <td align="left">
          <input id="imei2" type="hidden" name="IMEI2" value="" /> 
          <input id="device2" type="radio" name="DEVICE" value="2" /> 
          <span class="mainInputLabel">&nbsp;&nbsp;&nbsp;Peoplesoft Handset:</span></td>
          <td>
          <input id="psManufacturer2" class="mainInput" type="text" name="PSMAN2" style="width:150px;" value=""
          readonly="readonly" /><input id="psTac2" type="hidden" name="TAC2" value=""/>
          <input id="psModel2" class="mainInput" type="text" name="PSMOD2" style="width:150px;" value=""
          readonly="readonly" /></td>
        </tr>
        <tr id="psDevice3Row">
          <td align="left">
          <input id="imei3" type="hidden" name="IMEI3" value="" /> 
          <input id="device3" type="radio" name="DEVICE" value="3" /> 
          <span class="mainInputLabel">&nbsp;&nbsp;&nbsp;Peoplesoft Handset:</span></td>
          <td>
          <input id="psManufacturer3" class="mainInput" type="text" name="PSMAN3" style="width:150px;" value=""
          readonly="readonly" /><input id="psTac3" type="hidden" name="TAC3" value=""/>
          <input id="psModel3" class="mainInput" type="text" name="PSMOD3" style="width:150px;" value=""
          readonly="readonly" /></td>
        </tr>
        <tr id="tdsDeviceRow">
          <td align="left">
          <input id="imei4" type="hidden" name="IMEI4" value="" /> 
          <input id="device4" type="radio" name="DEVICE" value="4" /> 
          <span class="mainInputLabel">&nbsp;&nbsp;&nbsp;3DS Handset:</span></td>
          <td>
          <input id="tdsManufacturer" class="mainInput" type="text" name="TDSMAN" style="width:150px;" value=""
          readonly="readonly" /><input id="tdsTac" type="hidden" name="TAC_3DS" value=""/>
          <input id="tdsModel" class="mainInput" type="text" name="TDSMOD" style="width:150px;" value=""
          readonly="readonly" /></td>
        </tr>
      </table>
<BR><BR>
&nbsp;&nbsp;&nbsp;<input id="device5" type="radio" name="DEVICE" value="5"><span class="sectionLabel">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Manual Selection:</span><BR><BR><div id="manufacturerSelect"></div><input id="manualTac" type="hidden" name="TAC_MANUAL" value=""/><BR><div id="modelSelect"></div>
<a id="continueLinkIssue" class="continue" onclick="issueDeviceButtonClicked()" href="#"><img id="continueButton" src="/resolve/custom/images/Continue_button.jpg" alt="Continue"/></a>
<input id="submitLinkIssue" type="image" class="submit" name="EXECUTE" src="/resolve/custom/images/submit_button.jpg"/>
<a id="backLink" class="backIssue back" onclick="backButtonClicked()" href="#"><img id="backButton" src="/resolve/custom/images/back_button.jpg" alt="Continue"/></a>
</div>
<div class="section" id="serviceSection" style="display:none">
<span class="sectionLabel">Which service are you using?:</span>
<BR><BR>
<select id="service" class="mainInput" name="SERVICE" style="width:150px;" onfocus="selectReset(this)">
<option value="NONE">Select response...</option>
<option value="VOICE">Voice</option>
<option value="DATA">Data</option>
<option value="SMS">SMS</option>
<option value="ALL">All</option>
</select>
<BR>
<a id="continueLinkService" class="continue" onclick="serviceButtonClicked()" href="#"><img id="continueButton" src="/resolve/custom/images/Continue_button.jpg" alt="Continue"/></a>
<a id="backLink" class="backService back" onclick="backButtonClicked()" href="#"><img id="backButton" src="/resolve/custom/images/back_button.jpg" alt="Continue"/></a>
</div>
<div class="section" id="troubleshooterSection" style="display:none">
<span class="sectionLabel">Please provide a short description of the problem type in the field provided and follow the current troubleshooter to resolve the customer query:</span>
<BR><BR>
<textarea id="textDescription" name="TEXTDESCRIPTION" rows="5" cols="50" maxlength="250">
</textarea>
<BR><BR>
<input type="image" class="submit" name="MANUALDESC" src="/resolve/custom/images/submit_button.jpg"/>
<a id="backLink" class="back" onclick="backButtonClicked()" href="#"><img id="backButton" src="/resolve/custom/images/back_button.jpg" alt="Continue"/></a>
</div>
<div class="section" id="specificNumberSection" style="display:none">
<span class="sectionLabel">What is the number causing the problem?:</span><BR><BR><input id="specificNumber" class="mainInput" type="text" name="SPECIFICNUMBER" style="width:150px;" value="" onkeypress="return isPhoneEntry(event)"/><BR>
<a id="continueLink" class="continue" onclick="specificNumberButtonClicked()" href="#"><img id="continueButton" src="/resolve/custom/images/Continue_button.jpg" alt="Continue"/></a>
<a id="backLink" class="back" onclick="backButtonClicked()" href="#"><img id="backButton" src="/resolve/custom/images/back_button.jpg" alt="Continue"/></a>
</div>
<div class="section" id="locationSection" style="display:none">
<div class="specificAreas" id="specificAreasSection">
<span class="sectionLabel">Does the problem happen in specific areas?:</span><BR><BR>
<select id="specificAreas" class="mainInput" name="SPECIFICAREAS" style="width:300px;" onfocus="selectReset(this)">
<option value="NONE">Select response...</option>
<option value="SPECIFIC">SPECIFIC AREAS ONLY</option>
<option value="ALL">ALL AREAS</option>
<option value="UNKNOWN">HAVEN'T TRIED OTHER AREAS</option>
</select><BR><BR><BR><BR>
</div>
<span class="sectionLabel">Do you know the postcode where the problem happens, or the postcode where the problem last happened?:</span>
<BR><BR>
<table class="padded">
    <tr>
        <td align="left">
			<input id="location1" type="radio" name="LOCATION" value="1" /><span class="mainInputLabel">&nbsp;&nbsp;&nbsp;Postcode</span>:
		</td>
		<td>
			<input id="postcode" class="mainInput" type="text" name="POSTCODE" style="width:150px;vertical-align:middle;" value="" onkeypress="return isNumberDigitKey(event)" maxlength="8"/>
		</td>
	</tr>
	<tr>
		<td align="left" valign="top">
			<input id="location2" type="radio" name="LOCATION" value="2" /><span class="mainInputLabel">&nbsp;&nbsp;&nbsp;Partial Location:</span>
		</td>
		<td>
                   <table class="notPadded">
                     <tr>
			<td><div id="houseNumber"></div></td>
                        <td><div id="houseName"></div></td>
                     </tr>
                     <tr>
                        <td colspan="2"><div id="street"></div></td>
                     </tr>
                     <tr>
                        <td colspan="2"><div id="town"></div></td>
                     </tr>
                     <tr>
			<td colspan="2"><div id="county"></div></td>
                     </tr>
                     <tr>
                        <td><div id="addrPostcode"></div></td>
                     </tr>
                   </table>
		</td>
	</tr>
</table>
<a id="continueLinkLocation" class="continue" onclick="validateLocationQAS(this, event)" href="#"><img id="continueButton" src="/resolve/custom/images/Continue_button.jpg" alt="Continue"/></a>
<input id="submitLinkLocation" type="image" class="submit" name="EXECUTE" onclick="validateLocationQAS(this, event)" src="/resolve/custom/images/submit_button.jpg"/>
<a id="backLink" class="backLocation" onclick="backButtonClicked()" href="#"><img id="backButton" src="/resolve/custom/images/back_button.jpg" alt="Continue"/></a>
</div>
<div class="section occurredSection" style="display:none">
<span class="sectionLabel">When was the last time the issue happened?:</span>
<BR><BR>
</div>
<div class="section occurredSection" id="lastOccurredDateDiv"></div>
<div class="section occurredSection" id="lastOccurredTimeDiv"></div>
<div class="section occurredSection" style="display:none">
<a id="continueLink" class="continue" onclick="occurredButtonClicked()" href="#"><img id="continueButton" src="/resolve/custom/images/Continue_button.jpg" alt="Continue"/></a>
<a id="backLink" class="back" onclick="backButtonClicked()" href="#"><img id="backButton" src="/resolve/custom/images/back_button.jpg" alt="Continue"/></a>
</div>
<div class="section" id="timeOfDayIndoorsSignalSection" style="display:none">
<table class="padded">
<tr>
<td align="left">
<span class="sectionLabel">Does it happen at a specific time of day?:</span>
</td>
<td align="left">
<select id="timeOfDay" class="mainInput" name="TIMEOFDAY" style="width:300px;" onfocus="selectReset(this)">
<option value="NONE">Select response...</option>
<option value="ALLDAY">ALL DAY</option>
<option value="MORNING">MORNING</option>
<option value="AFTERNOON">AFTERNOON</option>
<option value="EVENING">EVENING</option>
</select>
</td>
</tr>
<tr>
<td align="left">
<span class="sectionLabel">Does it happen when you are indoors or outdoors?:</span>
</td>
<td align="left">
<select id="indoorsOutdoors" class="mainInput" name="INDOORSOUTDOORS" style="width:300px;" onfocus="selectReset(this)" onchange="todIndoorsSignalChanged(this)">
<option value="NONE">Select response...</option>
<option value="INDOORS">INDOORS</option>
<option value="OUTDOORS">OUTDOORS</option>
<option value="BOTH">INDOORS AND OUTDOORS</option>
</select>
</td>
</tr>
<tr>
<td align="left">
<span class="sectionLabel">Can you tell me how many bars of signal are on the phone?:</span>
</td>
<td align="left">
<select id="signalStrength" class="mainInput" name="SIGNALSTRENGTH" style="width:300px;" onfocus="selectReset(this)">
<option value="NONE">Select response...</option>
<option value="DONTKNOW">Don't Know</option>
<option value="FLUCTUATING">Signal is fluctuating</option>
<option value="NOSIGNAL">No Signal</option>
<option value="0 bars">0 bars</option>
<option value="1 bar">1 bar</option>
<option value="2 Bars">2 Bars</option>
<option value="3 Bars">3 Bars</option>
<option value="4 Bars">4 Bars</option>
<option value="5 Bars">5 Bars</option>
</select>
</td>
</tr>
</table>
<a id="continueLinkTodIndoorsSignal" class="continue" onclick="timeOfDayIndoorsSignalButtonClicked()" href="#"><img id="continueButton" src="/resolve/custom/images/Continue_button.jpg" alt="Continue"/></a>
<input id="submitLinkTodIndoorsSignal" type="image" class="submit" name="EXECUTE" src="/resolve/custom/images/submit_button.jpg" onclick="return timeOfDayIndoorsSignalSubmit()"/>
<a id="backLink" class="backTodIndoorsSignal" onclick="backButtonClicked()" href="#"><img id="backButton" src="/resolve/custom/images/back_button.jpg" alt="Continue"/></a>
</div>
<div class="section" id="homeSignalUnitSection" style="display:none">
<span class="sectionLabel">Do you use a Home Signal Unit in the affected postcode?:&nbsp;&nbsp;</span>
<select id="homeSignalUnit" class="mainInput" name="HOMESIGNALUNIT" style="width:100px;" onfocus="selectReset(this)">
<option value="NONE">Select...</option>
<option value="YES">YES</option>
<option value="NO">NO</option>
</select><BR>
<input type="image" class="submit" name="EXECUTE" src="/resolve/custom/images/submit_button.jpg"/>
<a id="backLink" class="back" onclick="backButtonClicked()" href="#"><img id="backButton" src="/resolve/custom/images/back_button.jpg" alt="Continue"/></a>
</div>
<div class="section" id="actionTaskSection">
{detail}
generate pre data entry page#h3g.lte.display
forward url parameters
{detail}
{detail}
experian validate location
{detail}
</div>
</td>
</tr>
</table>
</div><!-- #wrapper -->
</table>
</form>
<div id="pid" style="display:none;">{pre}$request.getAttribute("pid"){/pre}</div>
{section}
{section:type=SOURCE|title=Script_Form_Reset|id=rssection_1204424198730|height=300}
{pre}
<script type="text/javascript">
function formReset()
{
   var msisdn = document.getElementById('msisdn').value;
   
   var device1 = document.getElementById('device1').checked;
   var device2 = document.getElementById('device2').checked;
   var device3 = document.getElementById('device3').checked;
   var psManufacturer1 = document.getElementById('psManufacturer1').value;
   var psManufacturer2 = document.getElementById('psManufacturer2').value;
   var psManufacturer3 =document.getElementById('psManufacturer3').value;
   var psModel1 = document.getElementById('psModel1').value;
   var psModel2 = document.getElementById('psModel2').value;
   var psModel3 = document.getElementById('psModel3').value;
   var device4 = document.getElementById('device4').checked;
   var manufacturer = Ext.getCmp("manufacturerCombo").value;
   var model = Ext.getCmp("modelCombo").value;

   var form = document.getElementById('dataEntryForm');
   form.reset();
   Ext.getCmp("manufacturerCombo").setValue("");
   Ext.getCmp("modelCombo").setValue("");

   Ext.getCmp("lastOccurredDate").setValue(currentGMTDateTime());
   Ext.getCmp("lastOccurredTime").setValue(currentGMTDateTime());
   Ext.getCmp("lastOccurredTime").setMaxValue(currentGMTDateTime());
   Ext.getCmp("lastOccurredTime").setMinValue("00:00");

   document.getElementById('msisdn').value = msisdn;
   document.getElementById('device1').checked = false;
   document.getElementById('device2').checked = false;
   document.getElementById('device3').checked = false;
   document.getElementById('device4').checked = false;
   document.getElementById('psManufacturer1').value = psManufacturer1;
   document.getElementById('psManufacturer2').value = psManufacturer2;
   document.getElementById('psManufacturer3').value = psManufacturer3;
   document.getElementById('psModel1').value = psModel1;
   document.getElementById('psModel2').value = psModel2;
   document.getElementById('psModel3').value = psModel3;

   breadcrumb = [];
   position = 0;
   Ext.get('backButton').hide();
   breadcrumb.push('#issueDeviceSection');
   document.getElementById('msisdn').readOnly = false;
   var els = Ext.select('.section');
   els.setVisibilityMode(Ext.Element.DISPLAY);
   els.hide();
   Ext.select('#issueDeviceSection').show();
}
</script>
{/pre}
{section}
{section:type=SOURCE|title=Script_Is_Number_Key|id=rssection_588478727718|height=300}
{pre}
<script type="text/javascript">
function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : evt.keyCode
   if (charCode > 31 && (charCode < 48 || charCode > 57))
      return false;

   return true;
}
</script>
{/pre}
{section}
{section:type=SOURCE|title=Script_Click_Back_Button|id=rssection_1287974305520|height=300}
{pre}
<script type="text/javascript">
function todIndoorsSignalChanged(obj)
{
   var todIndoorsSignal = obj.options[obj.selectedIndex].value;
   if(todIndoorsSignal == "INDOORS")
   {
      var elSubmit = Ext.get("submitLinkTodIndoorsSignal");
      elSubmit.setVisibilityMode(Ext.Element.DISPLAY);
      elSubmit.hide();
      var elContinue = Ext.get("continueLinkTodIndoorsSignal");
      elContinue.setVisibilityMode(Ext.Element.DISPLAY);
      elContinue.show();
   }
   else
   {
      var elSubmit = Ext.get("submitLinkTodIndoorsSignal");
      elSubmit.setVisibilityMode(Ext.Element.DISPLAY);
      elSubmit.show();
      var elContinue = Ext.get("continueLinkTodIndoorsSignal");
      elContinue.setVisibilityMode(Ext.Element.DISPLAY);
      elContinue.hide();
   }
}
function issueSelectionChanged(obj)
{
   var issue = obj.options[obj.selectedIndex].value;
   if(issue != "MRCR" && issue != "MCR" && issue != "RCR")
   {
      var elSubmit = Ext.get("submitLinkIssue");
      elSubmit.setVisibilityMode(Ext.Element.DISPLAY);
      elSubmit.hide();
      var elContinue = Ext.get("continueLinkIssue");
      elContinue.setVisibilityMode(Ext.Element.DISPLAY);
      elContinue.show();
   }
   else
   {
      var elSubmit = Ext.get("submitLinkIssue");
      elSubmit.setVisibilityMode(Ext.Element.DISPLAY);
      elSubmit.show();
      var elContinue = Ext.get("continueLinkIssue");
      elContinue.setVisibilityMode(Ext.Element.DISPLAY);
      elContinue.hide();
   }
}
function backButtonClicked()
{
      if(position > 0) 
      {
         var el = breadcrumb[position - 1];
         Ext.select(".section").hide();
         Ext.select(el).show();
         breadcrumb.pop();
         position = position - 1;
         document.getElementById("step1").src = "/resolve/custom/images/Step1_off.png";
         document.getElementById("step2").src = "/resolve/custom/images/Step2_off.png";
         document.getElementById("step3").src = "/resolve/custom/images/Step3_off.png";
         document.getElementById("step4").src = "/resolve/custom/images/Step4_off.png";
         document.getElementById("step5").src = "/resolve/custom/images/Step5_off.png";
         document.getElementById("step6").src = "/resolve/custom/images/Step6_off.png";
         var step = imgBreadcrumb[position];
         if(step == "step1")
         {
            document.getElementById("step1").src = "/resolve/custom/images/Step1_on.png";
         }
         if(step == "step2")
         {
            document.getElementById("step2").src = "/resolve/custom/images/Step2_on.png";
         }
         if(step == "step3")
         {
            document.getElementById("step3").src = "/resolve/custom/images/Step3_on.png";
         }
         if(step == "step4")
         {
            document.getElementById("step4").src = "/resolve/custom/images/Step4_on.png";
         }
         if(step == "step5")
         {
            document.getElementById("step5").src = "/resolve/custom/images/Step5_on.png";
         }
         if(step == "step6")
         {
            document.getElementById("step6").src = "/resolve/custom/images/Step6_on.png";
         }
         imgBreadcrumb.pop();
      }
}
</script>
{/pre}
{section}
{section:type=SOURCE|title=Script_Select_Issue|id=rssection_1072199590621|height=300}
{pre}
<script type="text/javascript">
function issueDeviceButtonClicked()
{
   var eIssue = document.getElementById("issueReported");
   var issueReported = eIssue.options[eIssue.selectedIndex].value;
   var msisdn = document.getElementById('msisdn').value;
   var device = 0;
   if(document.getElementById('device1').checked){device=1;}
   if(document.getElementById('device2').checked){device=2;}
   if(document.getElementById('device3').checked){device=3;}
   if(document.getElementById('device4').checked){device=4;}
   if(document.getElementById('device5').checked){device=5;}

   Ext.get("backButton").show()
 
   if (msisdn.substring(0,3) != "447" || msisdn.length != 12)
   {
     Ext.MessageBox.show({title:'Invalid Value: MSISDN!',buttons: Ext.MessageBox.OK,icon: Ext.MessageBox.INFO,msg: 'Please make sure the MSISDN is in international format (begins with 447) and is exactly 12 digits'});
     return false;
   }
   else if (device==0 && issueReported != "O")
   {
     Ext.MessageBox.show({title:'Invalid Selection!',buttons: Ext.MessageBox.OK,icon: Ext.MessageBox.INFO,msg: 'Please select a device to use'});
     return false;
   }
   else if((device == 5 && (Ext.getCmp('modelCombo').getValue() == undefined || Ext.getCmp('manufacturerCombo').getValue() == undefined)) && issueReported != "O")
   {
      Ext.MessageBox.show({title:'Invalid Selection!',buttons: Ext.MessageBox.OK,icon: Ext.MessageBox.INFO,msg: 'Please select a device from the dropdown lists'});
      return false;         
   }
   else
   {
      document.getElementById('msisdn').readOnly = true;

      var e = document.getElementById("issueReported");
      var issue = e.options[e.selectedIndex].value;

      if(issue != "MIC" && issue != "RIC" && issue != "CPN")
      {
         var specificAreaEls = Ext.select(".specificAreas");
         specificAreaEls.setVisibilityMode(Ext.Element.DISPLAY);
         specificAreaEls.show();

         var elSubmit = Ext.get("submitLinkLocation");
         elSubmit.setVisibilityMode(Ext.Element.DISPLAY);
         elSubmit.hide();
         var elContinue = Ext.get("continueLinkLocation");
         elContinue.setVisibilityMode(Ext.Element.DISPLAY);
         elContinue.show();
      }

      if (issue=="NONE")
      {
        Ext.MessageBox.show({title:'Invalid Selection!',buttons: Ext.MessageBox.OK,icon: Ext.MessageBox.INFO,msg: 'Please select a problem type from the dropdown list'});
        return false;
      }
      else if(issue == "O")
      {
         Ext.select(".section").hide();
         Ext.select('#troubleshooterSection').show();
         breadcrumb.push('#troubleshooterSection');
         position = position + 1;
      }
      else
      {
               if(issue == "MRC" || issue == "MC" || issue == "RC" || issue == "DC" || issue == "SQ" || issue == "NB" || issue == "ECO" || issue == "NS" || issue == "DSMS" || issue == "DRT")
               {
                  Ext.select(".section").hide();
                  Ext.select('#locationSection').show();
                  breadcrumb.push('#locationSection');
                  position = position + 1;
                  document.getElementById("step1").src = "/resolve/custom/images/Step1_off.png";
                  document.getElementById("step3").src = "/resolve/custom/images/Step3_on.png";
                  imgBreadcrumb.push('step3');
               }
               else if(issue == "NNR" || issue == "CSN" || issue == "RSN" || issue == "MIC" || issue == "RIC" || issue == "CPN" || issue == "DRTSN")
               {
                  Ext.select(".section").hide();
                  Ext.select('#specificNumberSection').show();
                  breadcrumb.push('#specificNumberSection');
                  position = position + 1;
                  document.getElementById("step1").src = "/resolve/custom/images/Step1_off.png";
                  document.getElementById("step2").src = "/resolve/custom/images/Step2_on.png";
                  imgBreadcrumb.push('step2');
               }
               else
               {
                  Ext.select(".section").hide();
                  Ext.select('#submitSection').show();
                  breadcrumb.push('#submitSection');
                  position = position + 1;
               }
      }
   }
}
</script>
{/pre}
{section}
{section:type=SOURCE|title=Script_Select_SpecificNumber|id=rssection_404390731430|height=300}
{pre}
<script type="text/javascript">
function specificNumberButtonClicked()
{
   var eSpecificNumber = document.getElementById("specificNumber");
   var number = eSpecificNumber.value;
   number = number.replace(/\s/g,"");
   var phoneRegex = /^[+|0]{1}[0-9]{10,15}$/;

   var e = document.getElementById("issueReported");
   var issue = e.options[e.selectedIndex].value;

   if (issue=="NONE")
   {
     Ext.MessageBox.show({title:'Invalid Selection!',buttons: Ext.MessageBox.OK,icon: Ext.MessageBox.INFO,msg: 'Please select a location type from the dropdown list'});
     return false;
   }
   else if ( phoneRegex.test(number) == false )
   {
     Ext.MessageBox.show({title:'Invalid Number: Phone Number entry invalid!',buttons: Ext.MessageBox.OK,icon: Ext.MessageBox.INFO,msg: 'Please enter the number using the "+" format or for UK numbers the number can be entered with a single "0" prefix, e.g. +447700 900000 or 07700 900000. International numbers can have a maximum of 15 digits after the "+"'});
   }
   else if(issue == "MIC" || issue == "RIC" || issue == "CPN")
   {
      var specificAreaEls = Ext.select(".specificAreas");
      specificAreaEls.setVisibilityMode(Ext.Element.DISPLAY);
      specificAreaEls.hide();

      var elSubmit = Ext.get("submitLinkLocation");
      elSubmit.setVisibilityMode(Ext.Element.DISPLAY);
      elSubmit.hide();
      var elContinue = Ext.get("continueLinkLocation");
      elContinue.setVisibilityMode(Ext.Element.DISPLAY);
      elContinue.show();
     
      Ext.select(".section").hide();
      Ext.select('#locationSection').show();
      breadcrumb.push('#locationSection');
      position = position + 1;
      document.getElementById("step2").src = "/resolve/custom/images/Step2_off.png";
      document.getElementById("step3").src = "/resolve/custom/images/Step3_on.png";
      imgBreadcrumb.push('step3');
   }
   else if(issue == "DRTSN")
   {
      Ext.select(".section").hide();
      Ext.select('.occurredSection').show();
      breadcrumb.push('.occurredSection');
      position = position + 1;
      document.getElementById("step2").src = "/resolve/custom/images/Step2_off.png";
      document.getElementById("step4").src = "/resolve/custom/images/Step4_on.png";
      imgBreadcrumb.push('step4');
   }
   else
   {
      
      var specificAreaEls = Ext.select(".specificAreas");
      specificAreaEls.setVisibilityMode(Ext.Element.DISPLAY);
      specificAreaEls.show();

      var elSubmit = Ext.get("submitLinkLocation");
      elSubmit.setVisibilityMode(Ext.Element.DISPLAY);
      elSubmit.hide();
      var elContinue = Ext.get("continueLinkLocation");
      elContinue.setVisibilityMode(Ext.Element.DISPLAY);
      elContinue.show();

      Ext.select(".section").hide();
      Ext.select('#locationSection').show();
      breadcrumb.push('#locationSection');
      position = position + 1;
      document.getElementById("step2").src = "/resolve/custom/images/Step2_off.png";
      document.getElementById("step3").src = "/resolve/custom/images/Step3_on.png";
      imgBreadcrumb.push('step3');
   }
}
</script>
{/pre}
{section}
{section:type=SOURCE|title=Script_Select_Postcode|id=rssection_10413783571|height=300}
{pre}
<script type="text/javascript">
var locationValidationMask = new Ext.LoadMask(Ext.getBody(), {msg:"Validating Location....Please Wait"});
var whichLocationButtonClicked;

function checkPostCode(toCheck) 
{
  toCheck = toCheck.replace(/\s/g,"");
  var postCodeRegex = /^(((([A-PR-UWYZ][0-9][0-9A-HJKPS-UW]?)|([A-PR-UWYZ][A-HK-Y][0-9][0-9ABEHMNPRV-Y]?))[0-9]([ABD-HJLNP-UW-Z]{2}))|(GIR0AA))$/;

  return postCodeRegex.test(toCheck);
}

function locationButtonClicked()
{

   var e = document.getElementById("specificAreas");
   var area = e.options[e.selectedIndex].value;

   var e2 = document.getElementById("issueReported");
   var issue = e2.options[e2.selectedIndex].value;

   if ((area=="NONE") && (issue != "MIC" && issue != "RIC" && issue != "CPN"))
   {
     Ext.MessageBox.show({title:'Invalid Selection!',buttons: Ext.MessageBox.OK,icon: Ext.MessageBox.INFO,msg: 'Please select a problem area type from the dropdown list'});
     return false;
   }

   var postcode = document.getElementById("postcode").value.toUpperCase();

   var locationType = 0;
   if(document.getElementById('location1').checked){locationType=1;}
   if(document.getElementById('location2').checked){locationType=2;}

   if(locationType == 0)
   {
      Ext.MessageBox.show({title:'Invalid Entry',buttons: Ext.MessageBox.OK,icon: Ext.MessageBox.INFO,msg: 'Please select a location type and enter a value in the associated field!'});
      return false;
   }

   if((locationType == 1) && (postcode == "" || postcode == undefined))
   {
      Ext.MessageBox.show({title:'Invalid Entry',buttons: Ext.MessageBox.OK,icon: Ext.MessageBox.INFO,msg: 'You have selected home postcode but have not entered a postcode in the associated field!'});
      return false;
   }

   if((locationType == 2) && (((houseNumberText == "" || houseNumberText == undefined) && (houseNameText == "" || houseNameText == undefined)) || streetText == "" || streetText == undefined || townText == "" || townText == undefined || countyText == "" || countyText == undefined))
   {
      Ext.MessageBox.show({title:'Invalid Entry',buttons: Ext.MessageBox.OK,icon: Ext.MessageBox.INFO,msg: 'Please enter values for house name/number, street, town, and county!'});
      return false;
   }

   Ext.getCmp("lastOccurredTime").setMaxValue(currentGMTDateTime());
   Ext.getCmp("lastOccurredTime").setMinValue("00:00");
   Ext.getCmp("lastOccurredTime").setValue(currentGMTDateTime());
   Ext.getCmp("lastOccurredDate").setValue(currentGMTDateTime());

      Ext.select(".section").hide();
      Ext.select('.occurredSection').show();
      breadcrumb.push('.occurredSection');
      position = position + 1;
      document.getElementById("step3").src = "/resolve/custom/images/Step3_off.png";
      document.getElementById("step4").src = "/resolve/custom/images/Step4_on.png";
      imgBreadcrumb.push('step4'); 
}

function checkIfLocationValidationFinished()
{
   var locationType = 1;
   var locationId = "";

   if(document.getElementById('location1').checked)
   {
      locationType = 1;
      locationId = document.getElementById("postcode").value.toUpperCase();
      if(locationId == undefined || locationId == null)
      {
         locationId = "";
      }
      locationId = locationId.toUpperCase();
      locationId = locationId.replace(/ /g,'');
   }
   else if(document.getElementById('location2').checked)
   {
      locationType = 2;
      var houseNumber = "";
      var houseName = "";
      var street = "";
      var town = "";
      var county = "";
      if(Ext.get('houseNumberText') != null) 
      {
         houseNumber = Ext.getCmp("houseNumberText").getValue();
         if(houseNumber == undefined)
         {
            houseNumber = "";
         }
      }
      if(Ext.get('houseNameText') != null) 
      {
         houseName = Ext.getCmp("houseNameText").getValue();
         if(houseName == undefined)
         {
            houseName = "";
         }
      }
      if(Ext.get('streetText') != null) 
      {
         street = Ext.getCmp("streetText").getValue();
         if(street == undefined)
         {
            street = "";
         }
      }
      if(Ext.get('townText') != null) 
      {
         town = Ext.getCmp("townText").getValue();
         if(town == undefined)
         {
            town = "";
         }
      }
      if(Ext.get('countyText') != null) 
      {
         county = Ext.getCmp("countyText").getValue();
         if(county == undefined)
         {
            county = "";
         }
      }
      locationId = houseNumber + houseName + street + town + county;
      locationId = locationId.toUpperCase();
      locationId = locationId.replace(/ /g,'');
   }

   var returnId = locationId + "_locationValid";
   if(!Ext.get(returnId)){return false}else{return true}
} 
function retryLocationValidation()
{
   var buttonId = whichLocationButtonClicked.id;
   var form = document.forms["dataEntryForm"];

   var done = checkIfLocationValidationFinished();
   if(! done)
   {
       t=setTimeout(retryLocationValidation,1000);
   }
   else
   {
      var locationType = 1;
      var locationId = "";

      if(document.getElementById('location1').checked)
      {
         locationType = 1;
         locationId = document.getElementById("postcode").value.toUpperCase();
         if(locationId == undefined || locationId == null)
         {
            locationId = "";
         }
         locationId = locationId.toUpperCase();
         locationId = locationId.replace(/ /g,'');
      }
      else if(document.getElementById('location2').checked)
      {
         locationType = 2;
         var houseNumber = "";
         var houseName = "";
         var street = "";
         var town = "";
         var county = "";

         if(Ext.get('houseNumberText') != null) 
         {
            houseNumber = Ext.getCmp("houseNumberText").getValue();
            if(houseNumber == undefined)
            {
               houseNumber = "";
            }
         }
         if(Ext.get('houseNameText') != null) 
         {
            houseName = Ext.getCmp("houseNameText").getValue();
            if(houseName == undefined)
            {
               houseName = "";
            }
         }
         if(Ext.get('streetText') != null) 
         {
            street = Ext.getCmp("streetText").getValue();
            if(street == undefined)
            {
               street = "";
            }
         }
         if(Ext.get('townText') != null) 
         {
            town = Ext.getCmp("townText").getValue();
            if(town == undefined)
            {
               town = "";
            }
         }
         if(Ext.get('countyText') != null) 
         {
            county = Ext.getCmp("countyText").getValue();
            if(county == undefined)
            {
               county = "";
            }
         }

         locationId = houseNumber + houseName + street + town + county;
         locationId = locationId.toUpperCase();
         locationId = locationId.replace(/ /g,'');
      }

      var returnId = locationId + "_locationValid";

      if(Ext.get(returnId) != null)
      {
         var isValid = Ext.get(returnId).dom.innerHTML;

         if(isValid == "invalid")
         {
            locationValidationMask.hide();
            Ext.MessageBox.show({title:'Invalid Location',buttons: Ext.MessageBox.OK,icon: Ext.MessageBox.INFO,msg: 'The postcode or address is invalid!'});
            return false;
         }
         else if(isValid == "error")
         {
            locationValidationMask.hide();
            Ext.MessageBox.show({title:'Error Retrieving Location',buttons: Ext.MessageBox.OK,icon: Ext.MessageBox.INFO,msg: 'An error occurred while validating the location!'});
            return false;
         }
         else
         {
            if(locationType == 2)
            {
               Ext.getCmp('postcodeText').setValue(isValid);
            }

            if(buttonId == "submitLinkLocation")
            {
               form.submit();
            }
            else
            {
               locationButtonClicked();
            }
            locationValidationMask.hide();
         }
      }
      else
      {
         locationValidationMask.hide();
         Ext.MessageBox.show({title:'Error',buttons: Ext.MessageBox.OK,icon: Ext.MessageBox.INFO,msg: 'An error occurred when validating postcode!'});
      }
   }
}

function getProblemIdFromWiki() {
       var probIdValue = 'NEW';

       //get the div that has id starting with problemId
       var probIdDiv = __getProblemIdDiv();

       if(probIdDiv && probIdDiv.length > 0) {
               var probIdInnerHtml = probIdDiv[0].innerHTML;
               if(probIdInnerHtml && probIdInnerHtml.indexOf("$") == -1) {
                       probIdValue = probIdInnerHtml;
               }
       }
       return probIdValue;
}

function __getProblemIdDiv() {
       return Ext.DomQuery.select('div[id^=problemId]');
}

function executeRunbookUsingAjax(type, timeout)
{
    var houseNumber = "";
    var houseName = "";
    var street = "";
    var town = "";
    var county = "";

    var postcode = document.getElementById("postcode").value.toUpperCase();
    if(postcode == undefined || postcode == null)
    {
       postcode = "";
    }

    if(Ext.get('houseNumberText') != null) 
    {
       houseNumber = Ext.getCmp('houseNumberText').getValue();
       if(houseNumber == undefined)
       {
          houseNumber = "";
       }
    }
    if(Ext.get('houseNameText') != null) 
    {
       houseName = Ext.getCmp("houseNameText").getValue();
       if(houseName == undefined)
       {
          houseName = "";
       }
    }
    if(Ext.get('streetText') != null) 
    {
       street = Ext.getCmp("streetText").getValue();
       if(street == undefined)
       {
          street = "";
       }
    }
    if(Ext.get('townText') != null) 
    {
       town = Ext.getCmp("townText").getValue();
       if(town == undefined)
       {
          town = "";
       }
    }
    if(Ext.get('countyText') != null) 
    {
       county = Ext.getCmp("countyText").getValue();
       if(county == undefined)
       {
          county = "";
       }
    }
    var pId = Ext.get('pid').dom.innerHTML;

    if(type == 1)
    {
       var url = '/resolve/service/execute?AJAXCALL=true&action=EXECUTEPROCESS&PROBLEMID=' + pId + '&wiki=PHASE21.ValidateLocation&POSTCODE=' + postcode;
    }
    else
    {
       var url = '/resolve/service/execute?AJAXCALL=true&action=EXECUTEPROCESS&PROBLEMID=' + pId + '&wiki=PHASE21.ValidateLocation&HOUSE_NUMBER=' + houseNumber + '&HOUSE_NAME=' + houseName + '&STREET=' + street + '&TOWN=' + town + '&LOCALITY=' + county;
    }

    Ext.Ajax.request({
        url: url,
        success: function(response) {
            var text = response.responseText;
            var jsonObj = Ext.JSON.decode(text);
            var problemIdFromServer = jsonObj.problemId;

            try 
            {
                setTimeout(function() { retriggerExtjsElements(); }, timeout);
            } 
            catch(e)
            {
                alert("A problem occurred while sending the validation request");
            }
        },
        failure: function(response) 
        {
           alert("A problem occurred while sending the validation request");
        },
        scope: this
    });
}

function validateLocationQAS(obj, event)
{
   whichLocationButtonClicked = obj;
   //event.preventDefault();

   var locationType = 0;

   if(document.getElementById('location1').checked)
   {
      locationType = 1;
   }
   else if(document.getElementById('location2').checked)
   {
      locationType = 2;
   }

   var postcode = document.getElementById("postcode").value;

   var houseNumber = Ext.getCmp("houseNumberText").getValue();
   var houseName = Ext.getCmp("houseNameText").getValue();
   var street = Ext.getCmp("streetText").getValue();
   var town = Ext.getCmp("townText").getValue();
   var county = Ext.getCmp("countyText").getValue();

   var e = document.getElementById("specificAreas");
   var area = e.options[e.selectedIndex].value;

   var e2 = document.getElementById("issueReported");
   var issue = e2.options[e2.selectedIndex].value;

   if ((area=="NONE") && (issue != "MIC" && issue != "RIC" && issue != "CPN"))
   {
     Ext.MessageBox.show({title:'Invalid Selection!',buttons: Ext.MessageBox.OK,icon: Ext.MessageBox.INFO,msg: 'Please select a problem area type from the dropdown list'});
   }
   else if(locationType == 0)
   {
      Ext.MessageBox.show({title:'Invalid Selection',buttons: Ext.MessageBox.OK,icon: Ext.MessageBox.INFO,msg: 'Please select a location type!'});
   }
   else if(locationType == 2 && (((houseNumber == undefined || houseNumber == null || houseNumber == "") && (houseName == undefined || houseName == null || houseName == "")) || street == undefined || street == "" || street == null || town == undefined || town == "" || town == null))
   {
      Ext.MessageBox.show({title:'Invalid Entry',buttons: Ext.MessageBox.OK,icon: Ext.MessageBox.INFO,msg: 'Please enter values house number or house name, street and town!'});
   }
   else if(locationType == 1 && (postcode == undefined || postcode == "" || postcode == null))
   {
      Ext.MessageBox.show({title:'Invalid Entry',buttons: Ext.MessageBox.OK,icon: Ext.MessageBox.INFO,msg: 'Please enter a value for postcode!'});
   }
   else
   {

      locationValidationMask.show();

      if(document.getElementById('location1').checked)
      {
         executeRunbookUsingAjax(1, 10);
         retryLocationValidation();
      }
      else if(document.getElementById('location2').checked)
      {
         executeRunbookUsingAjax(2, 10);
         retryLocationValidation();
      }
      else
      {
         Ext.MessageBox.show({title:'Invalid Entry',buttons: Ext.MessageBox.OK,icon: Ext.MessageBox.INFO,msg: 'Please select a location type and enter a value in the associated field!'});
      }
   }
   event.returnValue(false);
}


</script>
{/pre}
{section}
{section:type=SOURCE|title=Script_Select_LastOccurred|id=rssection_1139429113473|height=300}
{pre}
<script type="text/javascript">
function serviceButtonClicked()
{
   var e = document.getElementById("service");
   var service = e.options[e.selectedIndex].value;

   if (service=="NONE")
   {
     Ext.MessageBox.show({title:'Invalid Selection!',buttons: Ext.MessageBox.OK,icon: Ext.MessageBox.INFO,msg: 'Please select a service type from the dropdown list'});
     return false;
   }
   else
   {
      Ext.select(".section").hide();
      Ext.select('#locationSection').show();
      breadcrumb.push('#locationSection');
      position = position + 1;
      document.getElementById("step3").src = "/resolve/custom/images/Step3_off.png";
      document.getElementById("step4").src = "/resolve/custom/images/Step4_on.png";
      imgBreadcrumb.push('step4');
   }
}
</script>
<script type="text/javascript">
function occurredButtonClicked()
{

   var lOccurDateTimeString = Ext.getCmp('lastOccurredDate').getSubmitValue()+" "+Ext.Date.format(Ext.getCmp('lastOccurredTime').getValue(),'H:i') ;
   var lOccurDateTime = Ext.Date.parse(lOccurDateTimeString, "d/m/Y H:i",true);

   var lOccurTime = Ext.getCmp('lastOccurredTime').getValue();

   if(Ext.getCmp("lastOccurredTime").isValid() == false || Ext.getCmp("lastOccurredDate").isValid() == false || Ext.getCmp("lastOccurredDate").getValue() == "" || Ext.getCmp("lastOccurredDate").getValue() == null || Ext.getCmp("lastOccurredTime").getValue() == "" || Ext.getCmp("lastOccurredTime").getValue() == null)
   {
      Ext.MessageBox.show({title:'Invalid Date and Time!',buttons: Ext.MessageBox.OK,icon: Ext.MessageBox.INFO,msg: 'Please select a valid date and time'});
      return false;
   }
   else
   {
      Ext.select(".section").hide();
      Ext.select('#timeOfDayIndoorsSignalSection').show();
      breadcrumb.push('#timeOfDayIndoorsSignalSection');
      position = position + 1;
      document.getElementById("step4").src = "/resolve/custom/images/Step4_off.png";
      document.getElementById("step5").src = "/resolve/custom/images/Step5_on.png";
      imgBreadcrumb.push('step5');
   }
}
</script>
{/pre}
{section}
{section:type=SOURCE|title=Script_Select_TimeOfDay|id=rssection_1182811394516|height=300}
{pre}
<script type="text/javascript">
function timeOfDayIndoorsSignalSubmit()
{
   var e1 = document.getElementById("timeOfDay");
   var timeOfDay = e1.options[e1.selectedIndex].value;
   var e2 = document.getElementById("indoorsOutdoors");
   var indoorsOutdoors = e2.options[e2.selectedIndex].value;
   var e3 = document.getElementById("signalStrength");
   var signalStrength = e3.options[e3.selectedIndex].value;

   if (timeOfDay=="NONE" || indoorsOutdoors=="NONE" || signalStrength=="NONE")
   {
     Ext.MessageBox.show({title:'Invalid Selection!',buttons: Ext.MessageBox.OK,icon: Ext.MessageBox.INFO,msg: 'Please select valid options from the dropdown lists'});
     return false;
   }
   else
   {
      return true;
   }
}
function timeOfDayIndoorsSignalButtonClicked()
{
   var e1 = document.getElementById("timeOfDay");
   var timeOfDay = e1.options[e1.selectedIndex].value;
   var e2 = document.getElementById("indoorsOutdoors");
   var indoorsOutdoors = e2.options[e2.selectedIndex].value;
   var e3 = document.getElementById("signalStrength");
   var signalStrength = e3.options[e3.selectedIndex].value;

   if (timeOfDay=="NONE" || indoorsOutdoors=="NONE" || signalStrength=="NONE")
   {
     Ext.MessageBox.show({title:'Invalid Selection!',buttons: Ext.MessageBox.OK,icon: Ext.MessageBox.INFO,msg: 'Please select valid options from the dropdown lists'});
     return false;
   }
   else if(indoorsOutdoors=="INDOORS")
   {
      Ext.select(".section").hide();
      Ext.select('#homeSignalUnitSection').show();
      breadcrumb.push('#homeSignalUnitSection');
      position = position + 1;
      document.getElementById("step5").src = "/resolve/custom/images/Step5_off.png";
      document.getElementById("step6").src = "/resolve/custom/images/Step6_on.png";
      imgBreadcrumb.push('step6');
   }
}
</script>
{/pre}
{section}
{section:type=SOURCE|title=Script_Select_Focus|id=rssection_611575555008|height=300}
{pre}
<script type="text/javascript">
function selectReset(obj)
{
   obj.value = "NONE";
}
</script>
{/pre}
{section}
{section:type=SOURCE|title=Script_Validate_Postcodes|id=rssection_185113589378|height=300}
{pre}
<script type="text/javascript">
function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : evt.keyCode
   if (charCode > 31 && (charCode < 48 || charCode > 57))
      return false;

   return true;
}
function isPhoneEntry(evt)
{
   var charCode = (evt.which) ? evt.which : evt.keyCode
   if (charCode == 35 || charCode == 43 || (charCode >= 48 && charCode <= 57) || charCode == 8)
      return true;

   return false;
}
</script>
{/pre}
{section}
{section:type=SOURCE|title=Script_Restrict_Postcodes|id=rssection_1009599809411|height=300}
{pre}
<script type="text/javascript">
function isNumberDigitKey(evt)
{
   var charCode = (evt.which) ? evt.which : evt.keyCode
   if ((charCode > 47 && charCode < 58) || (charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || charCode == 8 || charCode == 32)
      return true;

   return false;
}
</script>
{/pre}
{section}