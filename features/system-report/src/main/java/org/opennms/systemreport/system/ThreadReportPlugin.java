/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2010-2012 The OpenNMS Group, Inc.
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

package org.opennms.systemreport.system;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.util.TreeMap;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.opennms.systemreport.AbstractSystemReportPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

public class ThreadReportPlugin extends AbstractSystemReportPlugin {
    private static final Logger LOG = LoggerFactory.getLogger(ThreadReportPlugin.class);
    @Override
    public String getName() {
        return "Threads";
    }

    @Override
    public String getDescription() {
        return "Java thread dump (full output only)";
    }

    @Override
    public int getPriority() {
        return 10;
    }

    @Override
    public TreeMap<String, Resource> getEntries() {
        final TreeMap<String,Resource> map = new TreeMap<String,Resource>();

        final StringBuilder sb = new StringBuilder();
        try {
            final ThreadInfo[] threads = ManagementFactory.getThreadMXBean().dumpAllThreads(true, true);
            for (final ThreadInfo info : threads) {
                sb.append(info.toString());
            }

            map.put("ThreadDump.txt", getResource(sb.toString()));
        } catch (final Exception e) {
            LOG.debug("Unable to get thread dump.", e);
        }

        return map;
    }

    private void triggerThreadDump() {
        String kill = findBinary("kill");
        
        if (kill != null) {
            for (final Integer pid : getOpenNMSProcesses()) {
                LOG.debug("pid = {}", pid);
                CommandLine command = CommandLine.parse(kill + " -3 " + pid.toString());
                try {
                    LOG.trace("running '{}'", command);
                    DefaultExecutor executor = new DefaultExecutor();
                    executor.setWatchdog(new ExecuteWatchdog(5000));
                    int exitValue = executor.execute(command);
                    LOG.trace("finished '{}'", command);
                    if (exitValue != 0) {
                        LOG.warn("'{}' exited non-zero: {}", command, exitValue);
                    }
                } catch (final Exception e) {
                    LOG.warn("Unable to run kill -3 on '{}': you might need to run system-report as root.", pid, e);
                }
            }
        }
    }
}
