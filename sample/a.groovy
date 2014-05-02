  
def cli = new CliBuilder()  
cli.with  
{  
   h(longOpt: 'help', 'Help - Usage Information')  
   c(longOpt: 'city', 'City', type: String, required: true)  
   y(longOpt: 'county', 'County', type: String, required: true)   
   s(longOpt: 'state', 'State', type: String, required: true)  
}  
def opt = cli.parse(args)  
if (!opt) return  
if (opt.h) cli.usage()  
println "The location is ${opt.c}, ${opt.y}, ${opt.s}."  