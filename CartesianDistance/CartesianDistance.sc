CartesianDistance : KSubsets {

	*new { arg axisPairsArray;
		^this.get(axisPairsArray)
	}

	*get { arg axisPairsArray;
		var distance = Array.newClear(axisPairsArray.size);

		axisPairsArray.do{ arg item, i;
			distance[i] =  super.new(2, item).asArray;

			distance[i].do{ arg set, j; distance[i][j] = set.asArray.reduce('-') ** 2 };
		};

		distance = distance.lace(distance.size * distance[0].size).reshape(axisPairsArray.size, distance[0].size);

		distance.do{ arg item, i; distance[i] = sqrt(distance[i].sum) };

		^distance;
	}

	*average {arg axisPairsArray;
		var distance = this.get(axisPairsArray);
		^ distance.sum / distance.size;
	}

	*groupByAxis { arg dimensions, coordsArray, getDistance = false, getAverage = false;

		var grouped = coordsArray.lace(coordsArray.size * dimensions).reshape(dimensions, coordsArray.size);

		^if(getDistance == true, {
			if(getAverage == true, { "WARNING: getDistance must be false to return getAverage".postln });

			this.get(grouped);

		}, {
			if(getAverage == true, { this.average(grouped) }, { grouped })
		});
	}
}

