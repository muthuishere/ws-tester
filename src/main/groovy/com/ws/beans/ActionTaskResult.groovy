package com.ws.beans

class ActionTaskResult {
def name
def condition
def severity
def summary
def detail
def actionId
def duration
def completion
def lastupdated

def pushvar(String name,String val){
	name=name.toLowerCase();
	this[name]=val
	}
}
