CartesianDistance : KSubsets {

	*new { arg coordsArray, getDistance = true, getAverage = false;
		^this.get(coordsArray, getDistance, getAverage)
	}

	*calculate { arg axisPairsArray;
		var distance = Array.newClear(axisPairsArray.size);

		axisPairsArray.do{ arg item, i;
			distance[i] =  super.new(2, item).asArray;

			distance[i].do{ arg set, j; distance[i][j] = set.asArray.reduce('-') ** 2 };
		};

		distance = distance.flop;

		distance.do{ arg item, i; distance[i] = sqrt(distance[i].sum) };

		^distance;
	}

	*average {arg axisPairsArray;
		var distance = this.calculate(axisPairsArray);
		^ distance.sum / distance.size;
	}

	*get { arg coordsArray, getDistance = true, getAverage = false;

		var grouped = coordsArray.flop;

		^if(getDistance == true, {
			if(getAverage == true, { "WARNING: getDistance must be false to return getAverage".postln });

			this.calculate(grouped);

		}, {
			if(getAverage == true, { this.average(grouped) }, { grouped })
		});
	}
}

