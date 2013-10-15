/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2012 The OpenNMS Group, Inc.
 * OpenNMS(R) is Copyright (C) 1999-2012 The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is a registered trademark of The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * OpenNMS(R) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenNMS(R).  If not, see:
 *      http://www.gnu.org/licenses/
 *
 * For more information contact:
 *     OpenNMS(R) Licensing <license@opennms.org>
 *     http://www.opennms.org/
 *     http://www.opennms.com/
 *******************************************************************************/

package org.opennms.features.topology.app.internal.operations;

import java.util.List;

import org.opennms.features.topology.api.Constants;
import org.opennms.features.topology.api.GraphContainer;
import org.opennms.features.topology.api.Operation;
import org.opennms.features.topology.api.OperationContext;
import org.opennms.features.topology.api.support.VertexHopGraphProvider;
import org.opennms.features.topology.api.support.VertexHopGraphProvider.FocusNodeHopCriteria;
import org.opennms.features.topology.api.topo.VertexRef;

public class RemoveFocusVerticesOperation implements Constants, Operation {

	@Override
	public Undoer execute(List<VertexRef> targets, final OperationContext operationContext) {
		if (targets == null || targets.isEmpty()) return null;

		final GraphContainer graphContainer = operationContext.getGraphContainer();

		FocusNodeHopCriteria criteria = VertexHopGraphProvider.getFocusNodeHopCriteriaForContainer(graphContainer, false);

		if (criteria == null) {
			// Don't do anything... there is no existing VertexHopCriteria
		} else {
			for (VertexRef target : targets) {
				criteria.remove(target);
			}
			graphContainer.criteriaUpdated(criteria);

			// If there are no remaining focus vertices in the criteria, then
			// just remove it completely.
			if (criteria.size() == 0) {
				graphContainer.removeCriteria(criteria);
			}

			graphContainer.redoLayout();
		}

		return null;
	}

	@Override
	public boolean display(List<VertexRef> targets, OperationContext operationContext) {
		return enabled(targets, operationContext);
	}

	@Override
	public boolean enabled(List<VertexRef> targets, OperationContext operationContext) {
		if (targets == null || targets.isEmpty()) return false;

		final GraphContainer graphContainer = operationContext.getGraphContainer();

		FocusNodeHopCriteria criteria = VertexHopGraphProvider.getFocusNodeHopCriteriaForContainer(graphContainer, false);
		if (criteria == null) {
			return false;
		} else {
			for (VertexRef target : targets) {
				// If any of the vertices are currently in the criteria, return true
				if (criteria.contains(target)) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public String getId() {
		return "RemoveFocusVertices";
	}
}
