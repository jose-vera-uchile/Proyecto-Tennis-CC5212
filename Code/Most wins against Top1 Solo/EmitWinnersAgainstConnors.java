package org.mdp.hadoop.cli;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;


//En este obtenemos aquellos que ganaron contra connors, por lo que buscaremos que la llave sea Connors y 
/**
 * Java class to emit pairs of actresses/actors
 * based on appearing in the same movie
 *
 *The input CSV file contains te following (String[] split = inputLine.split("\t");):
 		* split[0] tournament of the match
 		* split[1] location of the tournament
 		* split[2] year of the match
 		* split[3] player 
 		* split[4] opponent
 		* split[5] sets_won
 		* split[6] is player_victory which indicates if is a victory or a defeat
 		* split[7] masters
 *
 *
 *
 * The input TSV file contains the following (String[] split = inputLine.split("\t");):
 		 * split[0] is the actor/actress name
		 * split[1] is the movie/tv-series name
		 * split[2] is the year (or -1 if not known)
		 * split[3] is the movie number for movies with the same name/year 
		 * 		e.g., "I", "II" or "null" if not given
		 * split[4] is the movie type; (see enum ActorMovieParser.MovieRole.MovieType) one of: 
		 * 		{ THEATRICAL_MOVIE, TV_SERIES, TV_MINI_SERIES, TV_MOVIE, VIDEO_MOVIE }
		 * split[5] is episode name (only for TV series)
		 * split[6] is for billing (number of appearance in credit or -1 if not given)
		 * split[7] is the role (e.g., character name ... "Michael Corleone")
		 * split[8] is the gender of the actor/actress
 * @author Aidan
 */
public class EmitWinnersAgainstConnors {
	
	public static String Connors = "jimmy-connors";
	
	/**
	 * This is the Mapper Class. This sends key-value pairs to different machines
	 * based on the key.
	 * 
	 * Remember that the generic is Mapper<InputKey, InputValue, MapKey, MapValue>
	 * 
	 * InputKey we don't care about (a LongWritable will be passed as the input
	 * file offset, but we don't care; we can also set as Object)
	 * 
	 * InputKey will be Text: a line of the file
	 * 
	 * MapKey will be Text: the movie name
	 * 
	 * MapValue will be Text: the actor name
	 * 
	 * @author Aidan
	 *
	 */
	public static class EmitWinnersMapper extends Mapper<Object, Text, Text, Text>{

		/**
		 * @throws InterruptedException 
		 * 
		 */
		@Override
		public void map(Object keyIn, Text valueIn,
				Context output)
						throws IOException, InterruptedException {
			String[] split = valueIn.toString().split(",");
			if (split[6].equals("t") & split[4].equals(Connors)) { //Entra aca si gano contra connors
				output.write(new Text(split[3]), new Text(split[4]));
			}
		}
	}
	
	//Haciendolo de esta forma, entonces tendremos las tuplas de la forma (ganador,[lista de Connors])
	
	/**
	 * This is the Reducer Class.
	 * 
	 * This collects sets of key-value pairs with the same key on one machine. 
	 * 
	 * Remember that the generic is Reducer<MapKey, MapValue, OutputKey, OutputValue>
	 * 
	 * MapKey will be Text: the movie name
	 * 
	 * MapValue will be Text: the actor name
	 * 
	 * OutputKey will be Text: an actor pairing
	 * 
	 * OutputValue will be IntWritable: the initial count
	 * 
	 * @author Aidan
	 *
	 */
	public static class EmitWinnersReducer extends 
	     Reducer<Text, Text, Text, IntWritable> {

		/**
		 * @throws InterruptedException 
		 * 
		 */
		@Override
		public void reduce(Text key, Iterable<Text> values,
				Context output) throws IOException, InterruptedException {
			ArrayList<String> list = new ArrayList<String>();
			
			for (Text valueIn: values) {
				list.add(valueIn.toString()); //Lista de los perdedores ante esa llave
			}
			
			Collections.sort(list);
			
			for(int i=0; i<list.size(); i++) {
				String tupla = key.toString();
				output.write(new Text(tupla), new IntWritable(1)); 
				//Tuplas de la forma (ganador,1) y habra una por cada vez que le gano a connors
			}
			
		}
	}

	/**
	 * Main method that sets up and runs the job
	 * 
	 * @param args First argument is input, second is output
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
		if (otherArgs.length != 2) {
			System.err.println("Usage: "+EmitWinnersAgainstConnors.class.getName()+" <in> <out>");
			System.exit(2);
		}
		String inputLocation = otherArgs[0];
		String outputLocation = otherArgs[1];
		
		Job job = Job.getInstance(new Configuration());
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		job.setMapperClass(EmitWinnersMapper.class);
		//job.setCombinerClass(null);
		job.setReducerClass(EmitWinnersReducer.class);
		
		
		//TODO set:
		// (1) job's map output key class
		// (2) job's map output value class
		// (3) job's (reduce) output key class
		// (4) job's (reduce) output value class
		// (5) job's mapper class
		// (6) job's combiner class (only if applicable)
		// (7) job's reducer class
		
		FileInputFormat.setInputPaths(job, new Path(inputLocation));
		FileOutputFormat.setOutputPath(job, new Path(outputLocation));
		
		job.setJarByClass(EmitWinnersAgainstConnors.class);
		job.waitForCompletion(true);
	}	
}
