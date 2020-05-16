/*
SuperCollider class for finding all possible subsets of size K
Translation from the Python implementation by Severyn Kozak in https://sevko.io/articles/power-set-algorithms/
*/

KSubsets : Collection {

	*new { arg k, set;
		^super.new.find(k, set.flat);
	}

	find { arg k, set;
		var subsets = Set(0);

		^if(k == 0, {
			[[]];
		}, {
			(set.size - k + 1).do{ |i|
				KSubsets(k - 1, set.copyRange(i + 1, set.size - 1)).do{ |subset|
					subsets.add(subset ++ [set[i]]);
				};
			};
			subsets;
		});
	}
}


